package edu.gatech.seclass.jobcompare6300;

// Toast message not shown class source:
// https://stackoverflow.com/questions/29896223/android-espresso-how-to-check-that-toast-message-is-not-shown

import android.view.View;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingRootException;
import androidx.test.espresso.base.DefaultFailureHandler;
import org.hamcrest.Matcher;

public class NoToastMessage implements FailureHandler {
    private final FailureHandler defaultHandler
            = new DefaultFailureHandler(InstrumentationRegistry.getTargetContext());
    @Override public void handle(Throwable error, Matcher<View> viewMatcher) {
        if (!(error instanceof NoMatchingRootException)) {
            defaultHandler.handle(error, viewMatcher);
        }
    }
}