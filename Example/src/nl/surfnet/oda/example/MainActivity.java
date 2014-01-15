package nl.surfnet.oda.example;

import java.util.List;

import nl.surfnet.oda.APIClient;
import nl.surfnet.oda.APIWrapper;
import nl.surfnet.oda.entity.Person;
import android.app.Activity;
import android.os.Bundle;
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
        // Create a new API client
        APIClient apiClient = new APIClient(this, "http://imogen.surfnet.nl:8001/");
        // Create a wrapper for the client
        APIWrapper apiWrapper = new APIWrapper(apiClient);
        // Retreive the persons info
        apiWrapper.getPersons(new APIWrapper.PersonsListener() {

            @Override
            public void onResponse(List<Person> persons) {
                // display a simple toast with the number of persons
                Toast.makeText(MainActivity.this, "There are " + persons.size() + " persons.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception error) {
                // if an error happened, inform the user
                Toast.makeText(MainActivity.this, "An error happened. Check if you have internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    }

}
