package edu.gatech.seclass.jobcompare6300;

// Toastmatcher class source:
// https://stackoverflow.com/questions/28390574/checking-toast-message-in-android-espresso

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


public class Toastmatcher extends TypeSafeMatcher<Root> {
    @Override    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                //means this window isn't contained by any other windows.
                return true;
            }
        }
        return false;
    }
}
