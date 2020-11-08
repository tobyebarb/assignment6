package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.databinding.ContactFormActivityBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    // TODO 01. Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 06. Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    private ContactFormActivityBinding mBinding;
    private TextView first_nameTV;
    private TextView last_nameTV;
    private TextView phone_numberTV;
    private TextView emailTV;
    private TextView addressTV;
    private TextView websiteTV;

    public static final String EXTRA_CONTACT = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ContactFormActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        first_nameTV = findViewById(R.id.first_name);
        last_nameTV = findViewById(R.id.last_name);
        phone_numberTV = findViewById(R.id.phone_number);
        emailTV = findViewById(R.id.email);
        addressTV = findViewById(R.id.location);
        websiteTV = findViewById(R.id.website);
    }

    public void onButtonClicked(View view) {
        Intent intent = new Intent(this, ContactInfoActivity.class);



        Contact contact = new Contact(first_nameTV.getText().toString(), last_nameTV.getText().toString(), phone_numberTV.getText().toString(),
                emailTV.getText().toString(), addressTV.getText().toString(), websiteTV.getText().toString());

        intent.putExtra(EXTRA_CONTACT, contact);
        startActivity(intent);
    }
}
