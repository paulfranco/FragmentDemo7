package co.paulfran.paulfranco.fragmentdemo7;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    FragmentManager manager;
    private static final String TAG = FragmentB.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        manager.addOnBackStackChangedListener(this);
    }

    public void addFragmentA(View view) {

        Fragment fragmentA = new FragmentA();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragmentA, "FragA");
        transaction.addToBackStack("AddFragA");
        transaction.commit();
    }

    public void removeFragmentA(View view) {

        FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("FragA");
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentA != null){
            transaction.remove(fragmentA);
            transaction.addToBackStack("RemFragA");
            transaction.commit();
        } else {
            Toast.makeText(this, "Fragment A not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void addFragmentB(View view) {
        Fragment fragmentB = new FragmentB();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, fragmentB, "FragB");
        transaction.addToBackStack("AddFragB");
        transaction.commit();
    }

    public void remFragmentB(View view) {
        FragmentB fragmentB = (FragmentB) manager.findFragmentByTag("FragB");
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragmentB != null){
            transaction.remove(fragmentB);
            transaction.addToBackStack("RemFragB");
            transaction.commit();
        } else {
            Toast.makeText(this, "Fragment B not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void replaceByFragmentA(View view) {

        FragmentA fragmentA = new FragmentA();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragmentA, "FragA");
        transaction.addToBackStack("RepByFragA");
        transaction.commit();

    }

    public void replaceByFragmentB(View view) {

        FragmentB fragmentB = new FragmentB();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragmentB, "FragB");
        transaction.addToBackStack("RepByFragB");
        transaction.commit();
    }

    public void attachFragmentA(View view) {

        // Get Reference to the detached fragment
        FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("FragA");
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragmentA != null) {
            transaction.attach(fragmentA);
            transaction.addToBackStack("AttachFragA");
            transaction.commit();
        } else {
            Toast.makeText(this, "FragmentA not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void detachFragmentA(View view) {

        FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("FragA");
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragmentA != null) {
            transaction.detach(fragmentA);
            transaction.addToBackStack("DetachFragA");
            transaction.commit();
        } else {
            Toast.makeText(this, "FragmentA not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void showFragmentA(View view) {

        FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("FragA");
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragmentA != null) {
            transaction.show(fragmentA);
            transaction.addToBackStack("ShowFragA");
            transaction.commit();
        } else {
            Toast.makeText(this, "FragmentA not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void hideFragmentA(View view) {

        FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("FragA");
        FragmentTransaction transaction = manager.beginTransaction();

        if (fragmentA != null) {
            transaction.hide(fragmentA);
            transaction.addToBackStack("HideFragA");
            transaction.commit();
        } else {
            Toast.makeText(this, "FragmentA not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void dummyBackButtonClick(View view) {

        manager.popBackStack();

    }

    public void pop_AddFragA_Inclusive_Transaction(View view) {
        // second parameter is either 0 or POP_BACK_STACK_INCLUSIVE
        manager.popBackStack("AddFragA", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void pop_AddFragB_Transaction(View view) {
        // second parameter is either 0 or POP_BACK_STACK_INCLUSIVE
        manager.popBackStack("AddFragB", 0);
    }

    @Override
    public void onBackStackChanged() {

        int length = manager.getBackStackEntryCount();

        StringBuilder msg = new StringBuilder("");

        for (int i = length - 1; i >= 0; i--) {

            msg.append("Index ").append(i).append(" : ");
            msg.append(manager.getBackStackEntryAt(i).getName());
            msg.append(" \n ");

        }

        Log.i(TAG, "\n" + msg.toString() + " \n ");

    }
}
