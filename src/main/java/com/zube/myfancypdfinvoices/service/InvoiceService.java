package com.zube.myfancypdfinvoices.service;

import com.zube.myfancypdfinvoices.model.Invoice;
import com.zube.myfancypdfinvoices.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class InvoiceService {

    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;
    private final String cdnUrl;

    public InvoiceService(UserService userService, JdbcTemplate jdbcTemplate ,@Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init(){
        System.out.println("Fetching PDF templates from S3 ....");
    }

    @PreDestroy
    public void shutdown(){
        System.out.println("Deleting downloaded templates ... ");
    }

    public List<Invoice> findAll(){

        return jdbcTemplate.query("select id, user_id, pdf_url, amount from invoices", (rs, rowNum) -> {

            Invoice invoice = new Invoice();

            invoice.setId(rs.getObject("id").toString());
            invoice.setUserId(rs.getString("user_id"));
            invoice.setPdfUrl(rs.getString("pdf_url"));
            invoice.setAmount(rs.getInt("amount"));

            return invoice;
        });
    }

    public Invoice create(String userId, Integer amount) {

        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into invoices (user_id, pdf_url, amount) values (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, generatedPdfUrl);
            preparedStatement.setInt(3, amount);

            return preparedStatement;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString() : null;

        Invoice invoice = new Invoice();

        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);

        return invoice;
    }

}
