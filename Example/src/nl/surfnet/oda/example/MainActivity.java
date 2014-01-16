package nl.surfnet.oda.example;

import java.util.List;

import nl.surfnet.oda.ListHandler;
import nl.surfnet.oda.persons.Person;
import nl.surfnet.oda.persons.PersonsClient;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example of how to use the Surfnet APIWrapper and the APIClient together.
 *
 * @author Daniel Zolnai
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView numberOfPersons = (TextView)findViewById(R.id.numberOfPersons);
        // Create a new client for getting the persons
        PersonsClient personsClient = new PersonsClient(this, "http://imogen.surfnet.nl:8001/");
        // Retrieve the persons info
        personsClient.getList(null, new ListHandler<Person>() {

            @Override
            public void onComplete(List<Person> list) {
                // display the number of persons in the UI
                numberOfPersons.setText("There are " + list.size() + " persons.");
            }

            @Override
            public void onError(Exception e) {
                // if an error happened, inform the user
                numberOfPersons.setText("Error :-(");
                Toast.makeText(MainActivity.this, "An error happened. Check if you have internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
