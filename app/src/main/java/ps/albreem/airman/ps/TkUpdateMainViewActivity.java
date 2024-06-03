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
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.widget.AdapterView;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import okhttp3.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class TkUpdateMainViewActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private boolean from_ = false;
	private boolean to_ = false;
	private boolean price_ = false;
	
	private ArrayList<HashMap<String, Object>> lms = new ArrayList<>();
	
	private LinearLayout update_main_form;
	private ListView listview1;
	private LinearLayout view_in_click;
	private LinearLayout update_layer;
	private LinearLayout Deletion_layer;
	private Button button1;
	private TextView textview1;
	private LinearLayout selection_option;
	private EditText edittext1;
	private Button edit_the_text;
	private Button from;
	private Button to;
	private Button price;
	private TextView textview2;
	private Button confirm_deletion;
	private LinearLayout linear10;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear11;
	private TextView textview3;
	private TextView tk_from;
	private TextView textview4;
	private TextView tk_to;
	private TextView textview5;
	private TextView tk_id;
	private TextView textview6;
	private TextView tk_price;
	
	private Intent ns = new Intent();
	private DatabaseReference db_ticket = _firebase.getReference("db/db_tickets");
	private ChildEventListener _db_ticket_child_listener;
	private TimerTask ne;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tk_update_main_view);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		update_main_form = findViewById(R.id.update_main_form);
		listview1 = findViewById(R.id.listview1);
		view_in_click = findViewById(R.id.view_in_click);
		update_layer = findViewById(R.id.update_layer);
		Deletion_layer = findViewById(R.id.Deletion_layer);
		button1 = findViewById(R.id.button1);
		textview1 = findViewById(R.id.textview1);
		selection_option = findViewById(R.id.selection_option);
		edittext1 = findViewById(R.id.edittext1);
		edit_the_text = findViewById(R.id.edit_the_text);
		from = findViewById(R.id.from);
		to = findViewById(R.id.to);
		price = findViewById(R.id.price);
		textview2 = findViewById(R.id.textview2);
		confirm_deletion = findViewById(R.id.confirm_deletion);
		linear10 = findViewById(R.id.linear10);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear11 = findViewById(R.id.linear11);
		textview3 = findViewById(R.id.textview3);
		tk_from = findViewById(R.id.tk_from);
		textview4 = findViewById(R.id.textview4);
		tk_to = findViewById(R.id.tk_to);
		textview5 = findViewById(R.id.textview5);
		tk_id = findViewById(R.id.tk_id);
		textview6 = findViewById(R.id.textview6);
		tk_price = findViewById(R.id.tk_price);
		
		listview1.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView abs, int _scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView abs, int _firstVisibleItem, int _visibleItemCount, int _totalItemCount) {
				
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				listview1.setVisibility(View.GONE);
				view_in_click.setVisibility(View.VISIBLE);
				Deletion_layer.setVisibility(View.GONE);
				update_layer.setVisibility(View.VISIBLE);
				button1.setVisibility(View.VISIBLE);
				map = new HashMap<>();
				map.put("id", lms.get((int)_position).get("id").toString());
				map.put("from", lms.get((int)_position).get("from").toString());
				map.put("to", lms.get((int)_position).get("to").toString());
				map.put("price", lms.get((int)_position).get("price").toString());
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				db_ticket.child(map.get("id").toString()).removeValue();
				ns.setClass(getApplicationContext(), TasksformanagerActivity.class);
					ns.putExtra("email", getIntent().getStringExtra("email"));
					startActivity(ns);
					finish();
			}
		});
		
		edittext1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edit_the_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (from_) {
					map.put("from", edittext1.getText().toString());
				}
				if (to_) {
					map.put("to", edittext1.getText().toString());
				}
				if (price_) {
					map.put("price", edittext1.getText().toString());
				}
				Deletion_layer.setVisibility(View.VISIBLE);
				update_layer.setVisibility(View.GONE);
				tk_from.setText(map.get("from").toString());
				tk_to.setText(map.get("to").toString());
				tk_id.setText(map.get("id").toString());
				tk_price.setText(map.get("price").toString());
				db_ticket.child(map.get("id").toString()).removeValue();
			}
		});
		
		from.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				from_ = true;
				to_ = false;
				price_ = false;
			}
		});
		
		to.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				to_ = true;
				from_ = false;
				price_ = false;
			}
		});
		
		price.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				price_ = true;
				to_ = false;
				from_ = false;
			}
		});
		
		confirm_deletion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!tk_id.getText().toString().equals("")) {
					db_ticket.child(map.get("id").toString()).updateChildren(map);
					map.clear();
					ns.setClass(getApplicationContext(), TasksformanagerActivity.class);
					ns.putExtra("email", getIntent().getStringExtra("email"));
					startActivity(ns);
					finish();
				}
			}
		});
		
		_db_ticket_child_listener = new ChildEventListener() {
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
		db_ticket.addChildEventListener(_db_ticket_child_listener);
	}
	
	private void initializeLogic() {
		listview1.setVisibility(View.VISIBLE);
		view_in_click.setVisibility(View.GONE);
		db_ticket.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lms = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lms.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				listview1.setAdapter(new Listview1Adapter(lms));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#0288D1")); }
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		edit_the_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		from.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		to.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		confirm_deletion.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_from.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_to.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		from.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		to.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		price.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		edit_the_text.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		confirm_deletion.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
	}
	
	@Override
	public void onBackPressed() {
		ns.setClass(getApplicationContext(), TasksformanagerActivity.class);
		ns.putExtra("email", getIntent().getStringExtra("email"));
		startActivity(ns);
		finish();
	}
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.custview, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView tk_from = _view.findViewById(R.id.tk_from);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView tk_to = _view.findViewById(R.id.tk_to);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView tk_id = _view.findViewById(R.id.tk_id);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView tk_price = _view.findViewById(R.id.tk_price);
			
			tk_from.setText(lms.get((int)_position).get("from").toString());
			tk_to.setText(lms.get((int)_position).get("to").toString());
			tk_id.setText(lms.get((int)_position).get("id").toString());
			tk_price.setText(lms.get((int)_position).get("price").toString());
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			tk_from.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			tk_to.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			tk_price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			tk_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			Animation animation; animation = AnimationUtils.loadAnimation( getApplicationContext(), android.R.anim.slide_in_left ); animation.setDuration(300); update_main_form.startAnimation(animation); animation = null;
			
			return _view;
		}
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