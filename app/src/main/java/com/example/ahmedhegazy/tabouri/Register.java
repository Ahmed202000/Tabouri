package com.example.ahmedhegazy.tabouri;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Connection;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Register extends AppCompatActivity {

    ImageView imgmap;
    EditText txtFullname, txtUsername, txtEmail, txtPassword, txtPhone, txtAddress, txtNational, txtActivation;
    Button btnCraeat, btnSend;

    int xx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //تعريف المتغيرات
        txtFullname = (EditText) findViewById(R.id.txtfullname);
        txtUsername = (EditText) findViewById(R.id.txtusername);
        txtEmail = (EditText) findViewById(R.id.txtemail);
        txtPassword = (EditText) findViewById(R.id.txtpassword);
        txtPhone = (EditText) findViewById(R.id.txtphone);
        txtAddress = (EditText) findViewById(R.id.txtaddress);
        txtNational = (EditText) findViewById(R.id.txtnational);
        txtActivation=(EditText)findViewById(R.id.txtactivation);
        btnCraeat = (Button) findViewById(R.id.btnCraeat);
        btnSend=(Button)findViewById(R.id.btnSendCode);

        //كود انشاء الحساب
        btnCraeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database db=new Database();
                Connection mina=db.ConnectDB();

                if(mina==null)
                    Toast.makeText(Register.this, "Check internet access", Toast.LENGTH_SHORT).show();
                else {
                    if (txtFullname.getText().toString().isEmpty()) {
                        txtFullname.setError("Please Enter Full Name");
                        txtFullname.requestFocus();
                    } else
                        {
                            if (txtActivation.getText().toString().isEmpty()) {
                                txtActivation.setError("Please Enter Activation");
                                txtActivation.requestFocus();
                            } else {
                                if (txtEmail.getText().toString().isEmpty()) {
                                    txtEmail.setError("Please Enter Email");
                                    txtEmail.requestFocus();
                                } else {
                                    if (txtPhone.getText().toString().isEmpty()) {
                                        txtPhone.setError("Please Enter Phone");
                                        txtPhone.requestFocus();
                                    } else {
                                        if (txtPassword.getText().toString().isEmpty()) {
                                            txtPassword.setError("Please Enter Password");
                                            txtPassword.requestFocus();
                                        } else {
                                            if (txtNational.getText().toString().isEmpty()) {
                                                txtNational.setError("Please Enter National");
                                                txtNational.requestFocus();
                                            } else {
                                                if (txtUsername.getText().toString().isEmpty()) {
                                                    txtUsername.setError("Please Enter User Name");
                                                    txtUsername.requestFocus();
                                                } else {
                                                    if (txtAddress.getText().toString().isEmpty()) {
                                                        txtAddress.setError("Please Enter Address");
                                                        txtAddress.requestFocus();
                                                    } else {
                                                        if (xx == Integer.parseInt(txtActivation.getText().toString())) {
                                                            String msg = db.RUNDML("insert into  Customer values('" + txtFullname.getText() + "','" + txtUsername.getText() + "','" + txtEmail.getText() + "','" + txtPassword.getText() + "','" + txtPhone.getText() + "','" + txtAddress.getText() + "','" + txtNational.getText() + "')");
                                                            if (msg.equals("Ok")) {
                                                                AlertDialog.Builder h = new AlertDialog.Builder(Register.this)
                                                                        .setTitle("Tabouri")
                                                                        .setMessage("Your account has been created succeed :) ")
                                                                        .setIcon(R.drawable.maps)
                                                                        .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                startActivity(new Intent(Register.this, Login.class));
                                                                            }
                                                                        });
                                                                h.create();
                                                                h.show();
                                                            } else if (msg.contains("PRIMARY KEY")) {
                                                                AlertDialog.Builder h = new AlertDialog.Builder(Register.this)
                                                                        .setTitle("Tabouri")
                                                                        .setMessage("This user is present :) ")
                                                                        .setIcon(R.drawable.maps)
                                                                        .setPositiveButton("Thanks", null);
                                                                h.create();
                                                                h.show();
                                                            } else
                                                                Toast.makeText(Register.this, "" + msg, Toast.LENGTH_SHORT).show();
                                                        }
                                                        else
                                                            Toast.makeText(Register.this, "Invaild activation code , check your email", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
        });


        //كود التحقق من الائميل
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String ss = "[a-zA-Z0-9._-]+@[" + "a-z]+\\.+[a-z]+";
                if (txtEmail.getText().toString().matches(ss)) {
                    ;
                } else
                    txtEmail.setError("Invaild Email Address (user@domain)");

            }
        });
        //كود ارسال الاكتفيشن للائميل
        btnSend.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String ss = "[a-zA-Z0-9._-]+@[" + "a-z]+\\.+[a-z]+";
                if (txtEmail.getText().toString().matches(ss)) {

                    Random random = new Random();
                    xx= random.nextInt(9999 - 1111 + 1) + 1111;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                final String username = "yourmobileapp2017@gmail.com";
                                final String password = "okok2017";
                                Properties props = new Properties();
                                props.put("mail.smtp.auth", "true");
                                props.put("mail.smtp.starttls.enable", "true");
                                props.put("mail.smtp.host", "smtp.gmail.com");
                                props.put("mail.smtp.port", "587");

                                Session session = Session.getInstance(props,
                                        new javax.mail.Authenticator() {

                                            protected PasswordAuthentication getPasswordAuthentication() {
                                                return new PasswordAuthentication(username, password);
                                            }
                                        });


                                try {
                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress("yourmobileapp2017@gmail.com"));
                                    message.setRecipients(Message.RecipientType.TO,
                                            InternetAddress.parse(txtEmail.getText().toString()));
                                    message.setSubject("Lawyer office - Activation Code");
                                    message.setText("Dear : " + txtFullname.getText().toString() +
                                            "\n" + "Your activation code is : " +xx+ "\n" + " Thank you for registeration ");
                                    Transport.send(message);


                                } catch (MessagingException e) {
                                    throw new RuntimeException(e);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();

                    Toast.makeText(Register.this, "Activation code has been sent , Check your email", Toast.LENGTH_SHORT).show();

                } else
                    txtEmail.setError("Invaild Email Address (user@domain)");
            }

        });


    }
}



