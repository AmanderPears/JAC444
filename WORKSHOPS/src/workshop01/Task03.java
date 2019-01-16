package workshop01;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Task03 {

	public static void run() {
		Scanner getInput = new Scanner(System.in);
		
		System.out.print("Loan Amount: ");
		double principal = getInput.nextDouble();
		
		System.out.print("Number of Years: ");
		int period = getInput.nextInt();
		
		System.out.print("Annual Interest Rate: ");
		double rate = getInput.nextDouble();
	
		getInput.close();
		
		//format output
		DecimalFormat money = new DecimalFormat("$###,###,#00.00");
		
		//convert rate to monthly
		rate = (rate/100)/12;
		//convert period to months
		period *= 12;
		
		double monthly = principal*((rate*Math.pow(1+rate, period))/((Math.pow(1+rate, period))-1));
		
		System.out.format("\nMontly Payment: %15s\n", money.format(monthly));
		
		System.out.format("Total Payment : %15s\n\n", money.format(monthly*period));
		
		System.out.format("%15s%15s%15s%15s\n","Payment#","Interest","Principal","Balance");
		
		String a, b, c;
		for (int i = 1; i <= period; i++) {
			a = money.format(rate*principal);
			b = money.format(monthly-(rate*principal));
			principal-=(monthly-(rate*principal));
			c = money.format(principal);
			System.out.format("%15d%15s%15s%15s\n",i,a,b,c);
		}
	}
}
