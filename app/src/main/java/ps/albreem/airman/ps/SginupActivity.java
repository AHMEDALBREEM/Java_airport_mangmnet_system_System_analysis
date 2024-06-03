package ps.albreem.airman.ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
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
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.view.View;
import android.widget.CompoundButton;
import android.graphics.Typeface;
import okhttp3.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class SginupActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean accepted = false;
	private HashMap<String, Object> newuser = new HashMap<>();
	private String test = "";
	
	private LinearLayout linear1;
	private ImageView imageview1;
	private LinearLayout linear10;
	private LinearLayout linear8;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private LinearLayout linear13;
	private LinearLayout linear12;
	private Button button1;
	private Button button2;
	private LinearLayout linear11;
	private TextView textview1;
	private EditText email;
	private TextView textview2;
	private EditText password;
	private TextView textview3;
	private EditText age;
	private TextView textview4;
	private EditText country;
	private CheckBox checkbox1;
	private LinearLayout linear14;
	private TextView textview5;
	
	private Intent mover = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private Intent login = new Intent();
	private DatabaseReference db = _firebase.getReference("db/customer_info");
	private ChildEventListener _db_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.sginup);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		imageview1 = findViewById(R.id.imageview1);
		linear10 = findViewById(R.id.linear10);
		linear8 = findViewById(R.id.linear8);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear9 = findViewById(R.id.linear9);
		linear13 = findViewById(R.id.linear13);
		linear12 = findViewById(R.id.linear12);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		linear11 = findViewById(R.id.linear11);
		textview1 = findViewById(R.id.textview1);
		email = findViewById(R.id.email);
		textview2 = findViewById(R.id.textview2);
		password = findViewById(R.id.password);
		textview3 = findViewById(R.id.textview3);
		age = findViewById(R.id.age);
		textview4 = findViewById(R.id.textview4);
		country = findViewById(R.id.country);
		checkbox1 = findViewById(R.id.checkbox1);
		linear14 = findViewById(R.id.linear14);
		textview5 = findViewById(R.id.textview5);
		auth = FirebaseAuth.getInstance();
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (accepted) {
					if (!email.getText().toString().equals("")) {
						if (!password.getText().toString().equals("")) {
							if (Double.parseDouble(age.getText().toString()) > 17) {
								if (country.getText().toString().equals("Palestine")) {
									auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(SginupActivity.this, _auth_create_user_listener);
								}
							}
						}
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "check the Rules of the system !!");
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mover.setClass(getApplicationContext(), LoginActivity.class);
				mover.putExtra("email", "none");
				mover.putExtra("key", "null");
				startActivity(mover);
				finish();
			}
		});
		
		checkbox1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					accepted = true;
				}
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mover.setAction(Intent.ACTION_VIEW);
				mover.setData(Uri.parse("https://albreem.blogspot.com/2023/05/blog-post.html?m=1"));
				startActivity(mover);
			}
		});
		
		_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db.addChildEventListener(_db_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					newuser = new HashMap<>();
					newuser.put("email", email.getText().toString());
					newuser.put("password", password.getText().toString());
					newuser.put("balance", "10000");
					test = email.getText().toString();
					
					 test=test.split("@")[0];
					
					db.child(test).updateChildren(newuser);
					newuser.clear();
					login.setClass(getApplicationContext(), LoginActivity.class);
					login.putExtra("email", email.getText().toString());
					login.putExtra("key", "1");
					startActivity(login);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		accepted = false;
		password.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
		password.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		country.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#0288D1")); }
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		password.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		age.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		country.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		checkbox1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		button2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
	}
	
	@Override
	public void onBackPressed() {
		mover.setClass(getApplicationContext(), LoginActivity.class);
		mover.putExtra("key", "0");
		mover.putExtra("email", "none");
		startActivity(mover);
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