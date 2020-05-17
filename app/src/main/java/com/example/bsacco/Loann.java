package com.example.bsacco;

import java.sql.Timestamp;

public class Loann {
    private String user_id;
    private String loan_amount;
    private  String loan_type;
    private Timestamp timestamp;

    public Loann() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

