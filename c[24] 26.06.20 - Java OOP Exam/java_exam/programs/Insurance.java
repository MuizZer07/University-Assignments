import java.util.ArrayList;

class Insurance implements Commissioned, StringKeyed{
 String id;
 double premium;
 double commissionRate;
 
 public Insurance(String id, double premium, double commissionRate){
   this.id = id;
   this.premium = premium;
   this.commissionRate = commissionRate;
 }
 
 public double getCommission() {
   return commissionRate * premium;
 }
 
 public static void insuranceCommisionTotal(ArrayList<Commissioned> commisions) {
     double totalCommission = 0.0;
     for(Commissioned commission: commisions) {
       totalCommission += commission.getCommission();
     };
 
     System.out.println(totalCommission);
   }

 @Override
 public String getStringKey() {
	return id + " " + this.premium + " " + this.commissionRate + " " + this.getCommission();
 }

}

