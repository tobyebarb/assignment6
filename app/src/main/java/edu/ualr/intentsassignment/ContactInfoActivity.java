package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.databinding.ContactInfoActivityBinding;
import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {

    private ContactInfoActivityBinding mBinding;
    private TextView nameTV;
    private TextView phone_numberTV;
    private TextView emailTV;
    private TextView addressTV;
    private TextView websiteTV;

    private Contact contact;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ContactInfoActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        contact = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);

        nameTV = findViewById(R.id.result_name);
        phone_numberTV = findViewById(R.id.result_phone_number);
        emailTV = findViewById(R.id.result_email);
        addressTV = findViewById(R.id.result_location);
        websiteTV = findViewById(R.id.result_website);

        updateInfo(mBinding.getRoot());
    }

    public void updateInfo(View view) {
        nameTV.setText(contact.getFullName());
        phone_numberTV.setText(contact.getPhoneNumber());
        emailTV.setText(contact.getEmailAddress());
        addressTV.setText(contact.getAddress());
        websiteTV.setText(contact.getWebsite());
    }

    public void onPhoneClick(View view) {
        String phoneNumberUri = "tel:"+contact.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumberUri));
        startActivity(intent);
    }

    public void onMessageClick(View view) {
        String smsUri = "smsto:" + contact.getPhoneNumber();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsUri));
        startActivity(intent);
    }

    public void onMapsClick(View view) {
        String place = contact.getAddress();
        String placeUri = String.format("geo:0,0?q=(%s)", place);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(placeUri));
        startActivity(intent);
    }

    public void onEmailClick(View view) {
        String emailSubject = "";
        String emailText = "";
        String emailReceiverList[] = {contact.getEmailAddress()};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        startActivity(Intent.createChooser(intent, "To complete action choose: "));
    }

    public void onWebClick(View view) {
        String webUri = contact.getWebsite();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUri));
        startActivity(intent);
    }

    // TODO 03. Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04. Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.
    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step
    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step
}
