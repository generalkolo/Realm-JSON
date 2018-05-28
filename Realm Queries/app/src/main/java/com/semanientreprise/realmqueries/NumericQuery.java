package com.semanientreprise.realmqueries;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumericQuery extends Fragment {

    @BindView(R.id.query_name)
    EditText queryName;
    @BindView(R.id.result)
    TextView resultTv;
    Unbinder unbinder;

    private Realm realm;

    public NumericQuery() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.numeric_query_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        realm = Realm.getDefaultInstance();
        return view;
    }

    @OnClick(R.id.go_btn)
    public void onViewClicked() {
        String age = queryName.getText().toString();

        StringBuilder toDisplay = new StringBuilder();

        if (!age.isEmpty()) {
            RealmResults<Person> result = realm.where(Person.class)
                    .greaterThan("age", Integer.valueOf(age))
                    .findAll();

            toDisplay.append("greaterThan() Predicate\n\n");
            toDisplay.append("There are - " + result.size() + " Persons older than " + age + "\n\n");

            int i = 0;

            while (i < result.size()) {
                toDisplay.append(result.get(i).name + " with phone number : " + result.get(i).phone_number + " email : " + result.get(i).email + " Address :" + result.get(i).address + " and age : "+ result.get(i).age +"\n\n\n");
                i++;
            }

            result = realm.where(Person.class)
                    .lessThan("age",Integer.valueOf(age))
                    .findAll();

            toDisplay.append("lessThan() Predicate\n\n");
            toDisplay.append("There are - " + result.size() + " Persons younger than " + age + "\n\n");

            i = 0;
            while(i < result.size()){
                toDisplay.append(result.get(i).name+" with phone number : "+result.get(i).phone_number+" email : "+result.get(i).email+" and Address : "+result.get(i).address+ " and age : "+ result.get(i).age +"\n\n\n");
                i++;
            }

            result = realm.where(Person.class)
                    .greaterThanOrEqualTo("age",Integer.valueOf(age))
                    .findAll();

            toDisplay.append("greaterThanOrEqualTo() Predicate\n\n");
            toDisplay.append("There are - " + result.size() + " Persons older than " + age + " or "+age+" years old"+"\n\n");

            i = 0;
            while(i < result.size()){
                toDisplay.append(result.get(i).name+" with phone number : "+result.get(i).phone_number+" email : "+result.get(i).email+" and Address : "+result.get(i).address+ " and age : "+ result.get(i).age +"\n\n\n");
                i++;
            }

            result = realm.where(Person.class)
                    .lessThanOrEqualTo("age",Integer.valueOf(age))
                    .findAll();

            toDisplay.append("lessThanOrEqualTo() Predicate\n\n");
            toDisplay.append("There are - " + result.size() + " Persons younger than " + age + " or "+age+" years old"+"\n\n");

            i = 0;
            while(i < result.size()){
                toDisplay.append(result.get(i).name+" with phone number : "+result.get(i).phone_number+" email : "+result.get(i).email+" and Address : "+result.get(i).address+ " and age : "+ result.get(i).age +"\n\n\n");
                i++;
            }
            resultTv.setText(toDisplay.toString());
        }
        else
            showToast("Age Field cannot be empty");
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}