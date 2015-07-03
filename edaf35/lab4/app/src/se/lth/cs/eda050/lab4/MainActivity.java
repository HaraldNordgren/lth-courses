package se.lth.cs.eda050.lab4;

import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	EditText	threads;
	EditText	number;
	View		startButton;
	TextView	time;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		startButton	= findViewById(R.id.start_button);
		number		= (EditText)findViewById(R.id.sum);
		threads		= (EditText)findViewById(R.id.num_threads);
		time		= (TextView)findViewById(R.id.time);

		number.setText("10000000");
		threads.setText("10");

		if (time == null)
			setContentView(R.layout.fel); 
		else
			startButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (v != null)
			switch (v.getId()) {
			case R.id.start_button:
			{
				int		i;
				long		lb;
				long		ub;
				int		n;
				long		m;
				long		sum;
				long		d;
				long 		begin;
				long 		end;
				Work		work[];

				n = Integer.valueOf(threads.getText().toString());
				m = Long.valueOf(number.getText().toString());

				begin = System.currentTimeMillis();

				if (n > m)
					n = 1;

				work = new Work[n];

				d = m / n;

				lb = 1;
				ub = d;

				for (i = 1; i <= n; ++i) {

					work[i-1] = new Work(lb, ub);
					work[i-1].start();

					lb += d;

					if (i == n-1)
						ub = m;
					else
						ub += d;
				}

				sum = 0;

				for (i = 0; i < n; ++i) {
					try { work[i].join(); } catch (InterruptedException e) { }
					
					sum += work[i].getSum();
				}
	
				end = System.currentTimeMillis();

				time.setText("time = " + (end-begin) + " ms\n" + 
					 "sum  = " + (m * (m + 1) / 2));

				break;
			}
		}
	}
}
