package com.smartkirana.aims.aimsshop.views.fragments.billingAddress;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smartkirana.aims.aimsshop.R;
import com.smartkirana.aims.aimsshop.utils.AppUtils;
import com.smartkirana.aims.aimsshop.utils.PrefConstants;
import com.smartkirana.aims.aimsshop.views.activities.base.BaseFragment;
import com.smartkirana.aims.aimsshop.views.fragments.PaymentOption.PaymentMethod;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class BillingAddressFragment extends BaseFragment implements IBillingAddress.View, View.OnClickListener{
    View view;
    boolean isOpen = false;
    EditText firstName, lastName, companyName, address1, address2, city, postCode;
    Button continueButton;
    SharedPreferences prefs;
    private BillingAddressPresenterImpl presenter;
    String api_token, customer_id;
    RadioGroup addressRadio;
    RadioButton newAddressLayout;
    LinearLayout new_address_layout;
    Spinner spinnerAvaiable, spinnerCountry, spinnerState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_billing_details, parent, false);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        companyName = view.findViewById(R.id.companyName);
        address1 = view.findViewById(R.id.address1);
        address2 = view.findViewById(R.id.address2);
        city = view.findViewById(R.id.city);
        postCode = view.findViewById(R.id.postCode);
        continueButton = view.findViewById(R.id.continueButton);
        new_address_layout = view.findViewById(R.id.new_address_layout);
        newAddressLayout = view.findViewById(R.id.newAddressLayout);
        presenter = new BillingAddressPresenterImpl(this, new BillingAddressControllerImpl());

        continueButton.setOnClickListener(this);
        newAddressLayout.setOnClickListener(this);
        SharedPreferences prefs = getActivity().getSharedPreferences(PrefConstants.USER_DETAILS_PREF, MODE_PRIVATE);
        api_token = prefs.getString(PrefConstants.API_TOKEN, PrefConstants.DEFAULT_VALUE);
        customer_id = prefs.getString(PrefConstants.CUSTOMER_ID, PrefConstants.DEFAULT_VALUE);

        spinnerAvaiable = (Spinner) view.findViewById(R.id.availableAddress);
        spinnerCountry = (Spinner) view.findViewById(R.id.availableCountry);
        spinnerState = (Spinner) view.findViewById(R.id.availableState);
        presenter.getAvailableCountries(api_token);
        presenter.getAvailableAddress(api_token, customer_id);
        return view;
    }

    @NotNull
    @Override
    public String getFirstName() {
        return firstName.getText().toString();
    }

    @NotNull
    @Override
    public String getLastName() {
        return lastName.getText().toString();
    }

    @NotNull
    @Override
    public String getCompanyName() {
        return companyName.getText().toString();
    }

    @NotNull
    @Override
    public String getAddress1() {
        return address1.getText().toString();
    }

    @NotNull
    @Override
    public String getAddress2() {
        return address2.getText().toString();
    }

    @NotNull
    @Override
    public String getCity() {
        return city.getText().toString();
    }

    @NotNull
    @Override
    public String getPostCode() {
        return postCode.getText().toString();
    }

    @NotNull
    @Override
    public String getRegion() {
        return "2315";
    }

    @NotNull
    @Override
    public String getCountry() {
        return "149";
    }

    @NotNull
    @Override
    public void setFirstName() {
        AppUtils.setError(firstName);
    }

    @NotNull
    @Override
    public void setLastName() {
        AppUtils.setError(lastName);
    }

    @NotNull
    @Override
    public void setAddress1() {
        AppUtils.setError(address1);
    }

    @NotNull
    @Override
    public void setCity() {
        AppUtils.setError(city);
    }

    @NotNull
    @Override
    public void setRegion() {
        Toast.makeText(getContext(), "State Region Empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCountry() {
        Toast.makeText(getContext(), "State Region Empty", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onFailure(@NotNull String message) {
        AppUtils.showSnackBar(view, message);
    }

    @Override
    public void showStub(boolean show) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continueButton:
                if (!TextUtils.isEmpty(api_token) && !TextUtils.isEmpty(customer_id)) {
                    presenter.addAddress(api_token, customer_id);
                }
                break;
        }

    }

    @Override
    public void onSuccessCountry(@NotNull CountryModel countryModel) {
        if (countryModel != null) {
            List<CountryModel.Country> country = countryModel.getCountries();
            ArrayList<String> CountryName = new ArrayList<>();
            ArrayList<String> CountryId = new ArrayList<>();
            CountryName.add("Select Country");
            for (int i = 0; i < countryModel.getCountries().size(); i++) {
                CountryName.add(country.get(i).getName());
                CountryId.add(country.get(i).getCountryId());
            }
            if (CountryName != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, CountryName);
                adapter.setDropDownViewResource(R.layout.spinner_items);
                spinnerCountry.setAdapter(adapter);
                String text = spinnerCountry.getSelectedItem().toString();
                presenter.getAvailableState(api_token, "149");
            } else {
                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onSuccessState(@NotNull StateModel stateModel) {
        if (stateModel != null) {
            List<StateModel.Zone> zones = stateModel.getZone();
            ArrayList<String> StateName = new ArrayList<>();
            ArrayList<String> StateId = new ArrayList<>();
            StateName.add("Select State");
            for (int i = 0; i < zones.size(); i++) {
                StateName.add(zones.get(i).getName());
                StateId.add(zones.get(i).getZoneId());
            }
            if (StateName != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, StateName);
                adapter.setDropDownViewResource(R.layout.spinner_items);
                spinnerState.setAdapter(adapter);
            }
        }
    }
    @Override
    public void onSuccessAvailableAddress(@NotNull AvailableModel availableModel) {
        if (availableModel != null) {
            List<AvailableModel.Address> availableAddress = availableModel.getAddresses();
            ArrayList<String> address = new ArrayList<>();
            for (int i = 0; i < availableAddress.size(); i++) {
                String getFullAddress = availableAddress.get(i).getAddress1() + "," + availableAddress.get(i).getAddress2() + "," + availableAddress.get(i).getCity() + "," + availableAddress.get(i).getZone() + "," + availableAddress.get(i).getCountry();
                address.add(getFullAddress);
            }
            if (address != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, address);
                adapter.setDropDownViewResource(R.layout.spinner_items);
                spinnerAvaiable.setAdapter(adapter);
                String text = spinnerAvaiable.getSelectedItem().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public void onSuccess(@NotNull String addressId) {

        Toast.makeText(getContext(), addressId, Toast.LENGTH_SHORT).show();

        Fragment fragment = new PaymentMethod();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.paymentMethod, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }
}
