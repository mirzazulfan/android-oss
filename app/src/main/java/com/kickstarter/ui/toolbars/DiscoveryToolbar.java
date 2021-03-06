package com.kickstarter.ui.toolbars;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kickstarter.R;
import com.kickstarter.libs.KSString;
import com.kickstarter.services.DiscoveryParams;
import com.kickstarter.ui.activities.ActivityFeedActivity;
import com.kickstarter.ui.activities.CreatorDashboardActivity;
import com.kickstarter.ui.activities.DiscoveryActivity;
import com.kickstarter.ui.activities.SearchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class DiscoveryToolbar extends KSToolbar {
  @Bind(R.id.activity_feed_button) ImageButton activityFeedButton;
  @Bind(R.id.creator_dashboard_button) ImageButton creatorDashboardButton;
  @Bind(R.id.filter_text_view) TextView filterTextView;
  @Bind(R.id.menu_button) TextView menuButton;
  @Bind(R.id.search_button) ImageButton searchButton;

  private KSString ksString;

  public DiscoveryToolbar(final @NonNull Context context) {
    super(context);
  }

  public DiscoveryToolbar(final @NonNull Context context, final @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public DiscoveryToolbar(final @NonNull Context context, final @Nullable AttributeSet attrs, final int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    if (isInEditMode()) {
      return;
    }

    ButterKnife.bind(this);
    this.ksString = environment().ksString();
  }

  @OnClick(R.id.activity_feed_button)
  protected void activityFeedButtonClick() {
    final Context context = getContext();
    context.startActivity(new Intent(context, ActivityFeedActivity.class));
  }

  @OnClick(R.id.creator_dashboard_button)
  protected void creatorDashboardButtonClick() {
    final Context context = getContext();
    context.startActivity(new Intent(context, CreatorDashboardActivity.class));
  }

  @OnClick({R.id.menu_button, R.id.filter_text_view})
  protected void menuButtonClick() {
    final DiscoveryActivity activity = (DiscoveryActivity) getContext();
    activity.discoveryLayout().openDrawer(GravityCompat.START);
  }

  public void loadParams(final @NonNull DiscoveryParams params) {
    final DiscoveryActivity activity = (DiscoveryActivity) getContext();

    this.filterTextView.setText(params.filterString(activity, this.ksString, true, false));
  }

  @OnClick(R.id.search_button)
  public void searchButtonClick() {
    final Context context = getContext();
    context.startActivity(new Intent(context, SearchActivity.class));
  }
}
