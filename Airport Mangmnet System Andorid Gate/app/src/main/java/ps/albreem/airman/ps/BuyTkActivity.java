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
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
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
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import okhttp3.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class BuyTkActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> bp = new HashMap<>();
	private double balance = 0;
	private double price = 0;
	private String test = "";
	private HashMap<String, Object> user = new HashMap<>();
	private String password = "";
	private String email = "";
	private String tk_prics = "";
	private String id = "";
	private String from = "";
	private String to = "";
	private String balance_user_old = "";
	private String temp_id = "";
	private String test2 = "";
	private boolean booked = false;
	private String d = "";
	private String pr = "";
	private String tr = "";
	private boolean bo = false;
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear15;
	private LinearLayout linear14;
	private TextView visible_searching;
	private EditText id_tk;
	private LinearLayout linear5;
	private LinearLayout ticket_details;
	private LinearLayout tick_returned;
	private Button return_btn;
	private Button buy_btn;
	private TextView textview5;
	private LinearLayout linear2;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private TextView textview1;
	private TextView tk_from;
	private TextView textview2;
	private TextView tk_to;
	private TextView textview3;
	private TextView tk_id;
	private TextView textview4;
	private TextView tk_price;
	private TextView textview6;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear20;
	private TextView id__;
	private TextView i__d;
	private TextView pricee;
	private TextView price_;
	private TextView emaill;
	private TextView email__;
	
	private Intent miver = new Intent();
	private DatabaseReference db = _firebase.getReference("db/db_tickets");
	private ChildEventListener _db_child_listener;
	private DatabaseReference db_ticket = _firebase.getReference("db/db_bought_ticket");
	private ChildEventListener _db_ticket_child_listener;
	private DatabaseReference db_customer = _firebase.getReference("db/customer_info");
	private ChildEventListener _db_customer_child_listener;
	private DatabaseReference db_tickets_returned = _firebase.getReference("db/db_returned_tickets");
	private ChildEventListener _db_tickets_returned_child_listener;
	private TimerTask xyz;
	private TimerTask vj;
	private SharedPreferences a;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.buy_tk);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear15 = findViewById(R.id.linear15);
		linear14 = findViewById(R.id.linear14);
		visible_searching = findViewById(R.id.visible_searching);
		id_tk = findViewById(R.id.id_tk);
		linear5 = findViewById(R.id.linear5);
		ticket_details = findViewById(R.id.ticket_details);
		tick_returned = findViewById(R.id.tick_returned);
		return_btn = findViewById(R.id.return_btn);
		buy_btn = findViewById(R.id.buy_btn);
		textview5 = findViewById(R.id.textview5);
		linear2 = findViewById(R.id.linear2);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		textview1 = findViewById(R.id.textview1);
		tk_from = findViewById(R.id.tk_from);
		textview2 = findViewById(R.id.textview2);
		tk_to = findViewById(R.id.tk_to);
		textview3 = findViewById(R.id.textview3);
		tk_id = findViewById(R.id.tk_id);
		textview4 = findViewById(R.id.textview4);
		tk_price = findViewById(R.id.tk_price);
		textview6 = findViewById(R.id.textview6);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		id__ = findViewById(R.id.id__);
		i__d = findViewById(R.id.i__d);
		pricee = findViewById(R.id.pricee);
		price_ = findViewById(R.id.price_);
		emaill = findViewById(R.id.emaill);
		email__ = findViewById(R.id.email__);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		
		id_tk.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!id_tk.getText().toString().equals("")) {
					if (getIntent().getStringExtra("key").equals("buy")) {
						visible_searching.setVisibility(View.VISIBLE);
						xyz = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										ticket_details.setVisibility(View.VISIBLE);
										test = id_tk.getText().toString();
										db.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												map = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														map.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												
												try {
													from = _dataSnapshot.child(test).child("from").getValue().toString();
												} catch (NullPointerException e) {
													    e.printStackTrace();
												}
												if (!from.equals("")) {
													tk_from.setText(from);
												}
												
												try {
													to = _dataSnapshot.child(test).child("to").getValue().toString();
												} catch (NullPointerException e) {
													    e.printStackTrace();
												}
												if (!to.equals("")) {
													tk_to.setText(to);
												}
												
												try {
													    tk_prics = _dataSnapshot.child(test).child("price").getValue().toString();
												} catch (NullPointerException e) {
													    e.printStackTrace();
												}
												if (!tk_prics.equals("")) {
													tk_price.setText(tk_prics);
												}
												
												try {
													    id = _dataSnapshot.child(test).child("id").getValue().toString();
													
												} catch (NullPointerException e) {
													    
													    e.printStackTrace();
												}
												if (!id.equals("")) {
													tk_id.setText(id);
												}
												test2 = getIntent().getStringExtra("email");
												test2=test2.split("@")[0];
												db_customer.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot _dataSnapshot) {
														map = new ArrayList<>();
														try {
															GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
															for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																HashMap<String, Object> _map = _data.getValue(_ind);
																map.add(_map);
															}
														}
														catch (Exception _e) {
															_e.printStackTrace();
														}
														balance_user_old = _dataSnapshot.child(test2).child("balance").getValue().toString();
														password = _dataSnapshot.child(test2).child("password").getValue().toString();
													}
													@Override
													public void onCancelled(DatabaseError _databaseError) {
													}
												});
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
										vj = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														if (!id_tk.getText().toString().equals(id)) {
															SketchwareUtil.showMessage(getApplicationContext(), "Ticket Not Founed !");
															to = "";
															from = "";
															tk_prics = "";
															id = "";
															tk_to.setText("");
															tk_from.setText("");
															tk_price.setText("");
															tk_id.setText("");
														}
													}
												});
											}
										};
										_timer.schedule(vj, (int)(2000));
									}
								});
							}
						};
						_timer.schedule(xyz, (int)(10000));
						xyz = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										visible_searching.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(xyz, (int)(5000));
					}
					else {
						visible_searching.setVisibility(View.VISIBLE);
						xyz = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										tick_returned.setVisibility(View.VISIBLE);
										test2 = getIntent().getStringExtra("email");
										test2=test2.split("@")[0];
										db_ticket.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												map = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														map.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												String idText = id_tk.getText().toString();
												try {
													    
													d = _dataSnapshot.child(test2).child("id").getValue().toString();
													if(d.equals(idText)){tr=idText;bo=true;}else{tr="";bo=false;}
												} catch (NullPointerException e) {
													    // Handle the null reference exception
													    // You can log the exception or perform other error handling here
													    e.printStackTrace();
												}
												if (!tr.equals("")) {
													i__d.setText(tr);
												}
												if(bo){try {
														tk_prics = _dataSnapshot.child(test2).child("price").getValue().toString();
													} catch (NullPointerException e) {
														    e.printStackTrace();
													}}
												if (!tk_prics.equals("")) {
													price_.setText(tk_prics);
												}
												
												if(bo){try {
														email = _dataSnapshot.child(test2).child("email").getValue().toString();
													} catch (NullPointerException e) {
														    e.printStackTrace();
													}}
												
												if (!email.equals("")) {
													email__.setText(email);
												}
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
										test2 = getIntent().getStringExtra("email");
										test2=test2.split("@")[0];
										db_customer.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												map = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														map.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												balance_user_old = _dataSnapshot.child(test2).child("balance").getValue().toString();
												password = _dataSnapshot.child(test2).child("password").getValue().toString();
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
										vj = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														if (!id_tk.getText().toString().equals(id)) {
															SketchwareUtil.showMessage(getApplicationContext(), "Ticket Not Founed !");
															email = "";
															tr = "";
															tk_prics = "";
															i__d.setText("");
															price_.setText("");
															email__.setText("");
														}
													}
												});
											}
										};
										_timer.schedule(vj, (int)(2000));
										xyz = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														visible_searching.setVisibility(View.GONE);
													}
												});
											}
										};
										_timer.schedule(xyz, (int)(5000));
									}
								});
							}
						};
						_timer.schedule(xyz, (int)(5000));
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		return_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!i__d.getText().toString().equals("")) {
					if (!price_.getText().toString().equals("")) {
						if (!email__.getText().toString().equals("")) {
							test = getIntent().getStringExtra("email");
							test=test.split("@")[0];
							db_ticket.child(test).removeValue();
							bp = new HashMap<>();
							bp.put("id", i__d.getText().toString());
							bp.put("price", price_.getText().toString());
							bp.put("customer_email", getIntent().getStringExtra("email"));
							db_tickets_returned.child(test).updateChildren(bp);
							bp.clear();
							db_customer.child(test).removeValue();
							SketchwareUtil.showMessage(getApplicationContext(), "Returned Operation Done");
							user = new HashMap<>();
							user.put("email", getIntent().getStringExtra("email"));
							user.put("balance", String.valueOf((long)(Double.parseDouble(balance_user_old) + Double.parseDouble(price_.getText().toString()))));
							user.put("password", password);
							db_customer.child(test).updateChildren(user);
							user.clear();
							_notify("New Balance Update ", "Balance :".concat(String.valueOf((long)(Double.parseDouble(balance_user_old) + Double.parseDouble(price_.getText().toString())))));
							miver.setClass(getApplicationContext(), TasksforcustomerActivity.class);
							miver.putExtra("email", getIntent().getStringExtra("email"));
							startActivity(miver);
							finish();
						}
					}
				}
			}
		});
		
		buy_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!tk_id.getText().toString().equals("")) {
					if (!tk_from.getText().toString().equals("")) {
						if (!tk_to.getText().toString().equals("")) {
							if (!tk_price.getText().toString().equals("")) {
								if ((Double.parseDouble(balance_user_old) > Double.parseDouble(tk_prics)) || (Double.parseDouble(balance_user_old) == Double.parseDouble(tk_prics))) {
									if (tk_to.getText().toString().equals("Gaza") || (tk_to.getText().toString().equals("GAZA") || tk_to.getText().toString().equals("gaza"))) {
										a.edit().putString("a", "1").commit();
									}
									else {
										a.edit().putString("a", "2").commit();
									}
									db_customer.child(test2).removeValue();
									user.put("email", getIntent().getStringExtra("email"));
									user.put("balance", String.valueOf((long)(Double.parseDouble(balance_user_old) - Double.parseDouble(tk_prics))));
									user.put("password", password);
									db_customer.child(test2).updateChildren(user);
									user.clear();
									user = new HashMap<>();
									user.put("email", getIntent().getStringExtra("email"));
									user.put("id", tk_id.getText().toString());
									user.put("price", tk_price.getText().toString());
									db_ticket.child(test2).updateChildren(user);
									user.clear();
									_notify("New Balance Update ", "Balance : ".concat(String.valueOf((long)(Double.parseDouble(balance_user_old) - Double.parseDouble(tk_prics)))));
									SketchwareUtil.showMessage(getApplicationContext(), "Bought Operation Done ");
									miver.setClass(getApplicationContext(), TasksforcustomerActivity.class);
									miver.putExtra("email", getIntent().getStringExtra("email"));
									startActivity(miver);
									finish();
								}
								else {
									SketchwareUtil.showMessage(getApplicationContext(), "check your balance !!");
								}
							}
						}
					}
				}
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
		
		_db_customer_child_listener = new ChildEventListener() {
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
		db_customer.addChildEventListener(_db_customer_child_listener);
		
		_db_tickets_returned_child_listener = new ChildEventListener() {
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
		db_tickets_returned.addChildEventListener(_db_tickets_returned_child_listener);
	}
	
	private void initializeLogic() {
		visible_searching.setVisibility(View.GONE);
		buy_btn.setVisibility(View.GONE);
		return_btn.setVisibility(View.GONE);
		ticket_details.setVisibility(View.GONE);
		tick_returned.setVisibility(View.GONE);
		if (getIntent().getStringExtra("key").equals("buy")) {
			buy_btn.setVisibility(View.VISIBLE);
		}
		else {
			return_btn.setVisibility(View.VISIBLE);
		}
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#0288D1")); }
		visible_searching.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		id_tk.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		return_btn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		buy_btn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_from.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_to.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		tk_price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		id__.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		i__d.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		pricee.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		price_.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		email__.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		emaill.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		buy_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		return_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
	}
	
	@Override
	public void onBackPressed() {
		miver.setClass(getApplicationContext(), TasksforcustomerActivity.class);
		miver.putExtra("email", getIntent().getStringExtra("email"));
		startActivity(miver);
		finish();
	}
	public void _notify(final String _title, final String _description) {
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { CharSequence name = "Messages"; String description = "You have new messages"; int importance = NotificationManager.IMPORTANCE_MAX; NotificationChannel channel = new NotificationChannel("id 1", name, importance); channel.setDescription(description); NotificationManager notificationManager = getSystemService(NotificationManager.class); notificationManager.createNotificationChannel(channel); }
			
			Intent intent = new Intent(BuyTkActivity.this, BuyTkActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); PendingIntent pendingIntent = PendingIntent.getActivity(BuyTkActivity.this, 0, intent, 0); androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(BuyTkActivity.this, "id 1") .setSmallIcon(R.drawable.app_icon) .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) .setContentTitle(_title) .setContentText(_description) .setPriority(androidx.core.app.NotificationCompat.PRIORITY_MAX) .setContentIntent(pendingIntent) .setAutoCancel(true); androidx.core.app.NotificationManagerCompat notificationManager = androidx.core.app.NotificationManagerCompat.from(BuyTkActivity.this); notificationManager.notify(1, builder.build());
		} catch (Exception e) {Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); }
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