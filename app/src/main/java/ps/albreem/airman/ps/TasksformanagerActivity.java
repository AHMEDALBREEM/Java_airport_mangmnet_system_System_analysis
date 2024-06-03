package ps.albreem.airman.ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.graphics.Typeface;
import okhttp3.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class TasksformanagerActivity extends AppCompatActivity {
	
	private FloatingActionButton _fab;
	
	private LinearLayout linear1;
	private LinearLayout linear13;
	private LinearLayout linear3;
	private LinearLayout linear8;
	private LinearLayout linear4;
	private LinearLayout linear10;
	private LinearLayout linear2;
	private LinearLayout linear6;
	private Button ticket_creation;
	private LinearLayout linear9;
	private Button ticket_updating;
	private Button duty_updating;
	private LinearLayout linear11;
	private Button duty_creating;
	private Button employee_creation;
	
	private Intent exit = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tasksformanager);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		linear13 = findViewById(R.id.linear13);
		linear3 = findViewById(R.id.linear3);
		linear8 = findViewById(R.id.linear8);
		linear4 = findViewById(R.id.linear4);
		linear10 = findViewById(R.id.linear10);
		linear2 = findViewById(R.id.linear2);
		linear6 = findViewById(R.id.linear6);
		ticket_creation = findViewById(R.id.ticket_creation);
		linear9 = findViewById(R.id.linear9);
		ticket_updating = findViewById(R.id.ticket_updating);
		duty_updating = findViewById(R.id.duty_updating);
		linear11 = findViewById(R.id.linear11);
		duty_creating = findViewById(R.id.duty_creating);
		employee_creation = findViewById(R.id.employee_creation);
		
		ticket_creation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setClass(getApplicationContext(), TkAddViewActivity.class);
				exit.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(exit);
				finish();
			}
		});
		
		ticket_updating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setClass(getApplicationContext(), TkUpdateMainViewActivity.class);
				exit.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(exit);
				finish();
			}
		});
		
		duty_updating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setClass(getApplicationContext(), DyUpdateMainViewActivity.class);
				exit.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(exit);
				finish();
			}
		});
		
		duty_creating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setClass(getApplicationContext(), DyAddViewActivity.class);
				exit.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(exit);
				finish();
			}
		});
		
		employee_creation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setClass(getApplicationContext(), EmpAddViewActivity.class);
				exit.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(exit);
				finish();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finishAffinity();
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#0288D1")); }
		Animation animation;
		        animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		        animation.setDuration(500);
		        linear1.startAnimation(animation);
		        animation = null;
		ticket_creation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		ticket_updating.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		duty_creating.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		duty_updating.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		employee_creation.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		ticket_creation.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF0288D1));
		ticket_updating.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF0288D1));
		duty_creating.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF0288D1));
		duty_updating.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF0288D1));
		employee_creation.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFF0288D1));
		linear13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFFAFAFA));
	}
	
	@Override
	public void onBackPressed() {
		exit.setClass(getApplicationContext(), LoginActivity.class);
		exit.putExtra("email", getIntent().getStringExtra("email"));
		exit.putExtra("key", "0");
		startActivity(exit);
		finish();
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}