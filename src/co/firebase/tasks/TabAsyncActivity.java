package co.firebase.tasks;

import android.app.TabActivity;
import android.os.Bundle;

public abstract class TabAsyncActivity extends TabActivity implements FireTaskManager.BindTaskUIHandler {
	private FireTaskManager taskManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		taskManager = FireTaskManager.getTaskManager(this);
	}
	
	public FireTaskManager getAsyncTaskManager() {
		return this.taskManager;
	}

	@Override
	protected void onDestroy() {
		this.taskManager.onActivityDestroy();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		taskManager.onActivityPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		taskManager.onActivityResume();
	}

	@Override
	public final Object onRetainNonConfigurationInstance() {
		return taskManager.onActivityRetainNonConfigurationInstance();
	}
	
	public abstract FireTaskUIHandler<?, ?, ?> onBindPersistentTaskUI(FireTask<?,?,?> task);	
}