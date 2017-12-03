package sa.gov.hajj.meetings.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import sa.gov.hajj.meetings.Constants;
import sa.gov.hajj.meetings.R;
import sa.gov.hajj.meetings.adapters.LanguagesAdapter;
import sa.gov.hajj.meetings.helpers.LanguageHelper;
import sa.gov.hajj.meetings.interfaces.IClickListener;
import sa.gov.hajj.meetings.interfaces.ILanguageChangeListener;

/**
 * Created by mustafa on 3/31/17.
 * Release the GEEK
 */

public class LanguageSelectorSheet extends BottomSheetDialogFragment implements IClickListener {

    /***
     * Flags
     */
    private static final String LANGUAGE_INDEX_KEY = "LanguageIndex";

    /***
     * Views
     */
    @BindView(R.id.rvLanguages) RecyclerView rvLanguages;

    /***
     * Vars
     */
    private int mLanguageIndex = LanguageHelper.LANGUAGE_ENGLISH_INDEX;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    public static LanguageSelectorSheet newInstance(int languageIndex) {

        Bundle args = new Bundle();

        LanguageSelectorSheet fragment = new LanguageSelectorSheet();
        args.putInt(LANGUAGE_INDEX_KEY, languageIndex);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        new LanguageHelper().initLanguage(getActivity(), true);
        View contentView = View.inflate(getContext(), R.layout.fragment_language_selector, null);
        ButterKnife.bind(this, contentView);
        rvLanguages.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvLanguages.setHasFixedSize(true);
        rvLanguages.setAdapter(new LanguagesAdapter(Constants.APP_LANGUAGES, getActivity(), this));
        dialog.setContentView(contentView);
        if (getArguments() != null && getArguments().get(LANGUAGE_INDEX_KEY) != null)
            mLanguageIndex = getArguments().getInt(LANGUAGE_INDEX_KEY);
        setLanguage(mLanguageIndex, false);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    private void setLanguage(int languageIndex, boolean informListener) {
        mLanguageIndex = languageIndex;
        if (informListener && getActivity() != null && getActivity() instanceof ILanguageChangeListener) {
            new LanguageHelper().changeLanguage(getActivity(), languageIndex);
            ((ILanguageChangeListener) getActivity()).LanguageChanged();
            dismiss();
        }
    }

    @Override public void ItemClicked(int position, View view) {
        setLanguage(position, true);
    }
}
