package se.lth.cs.eda050.lab4;

public class Work extends Thread {
	private	long	from;
	private	long	to;
	private	long	sum;

	public Work(long from, long to)
	{
		this.from	= from;
		this.to		= to;
		this.sum	= 0;
	}

	public void run()
	{
		long	i;

		for (i = from; i <= to; i++)
			sum += i;
	}

	public long getSum()
	{
		return sum;
	}
}
