package sa.gov.hajj.meetings.adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sa.gov.hajj.meetings.R;
import sa.gov.hajj.meetings.interfaces.IClickListener;
import sa.gov.hajj.meetings.viewModels.LanguageViewModel;


/**
 * Recycler View Adapter for app languages
 */
public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguageViewHolder> {

    /***
     * Vars
     */
    private ArrayList<LanguageViewModel> mNotifications;
    private IClickListener mListener;
    private Activity mActivity;
    private int lastPosition = -1;

    public LanguagesAdapter(ArrayList<LanguageViewModel> notifications, Activity activity, IClickListener listener) {
        this.mNotifications = notifications;
        this.mListener = listener;
        this.mActivity = activity;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LanguageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_language, parent, false));
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, int position) {
        LanguageViewModel language = mNotifications.get(position);
        holder.tvLanguageName.setText(mActivity.getString(language.getNameResource()));
        holder.ivLanguageFlag.setImageDrawable(ContextCompat.getDrawable(mActivity, language.getImage()));
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mActivity, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override public void onViewDetachedFromWindow(LanguageViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    /**
     * View Holder to language row
     */
    class LanguageViewHolder extends RecyclerView.ViewHolder {

        /***
         * Views
         */
        @BindView(R.id.ivLanguageFlag) ImageView ivLanguageFlag;
        @BindView(R.id.tvLanguageName) TextView tvLanguageName;
        @BindView(R.id.vCont) View mRootLayout;

        LanguageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.vCont) void ItemPressed(View v) {
            if (mListener != null)
                mListener.ItemClicked(getAdapterPosition(), v);
        }

        void clearAnimation() {
            mRootLayout.clearAnimation();
        }

    }
}
