import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
 class myPhone {
    private int x,y;
    StringBuilder phoneNumber= new StringBuilder("016");
    double distance ;
    baseStation bs;
    static int n =1;
    int id;
     public int getX() {
         return x;
     }

     public StringBuilder getPhoneNumber() {
         return phoneNumber;
     }

     public double getDistance() {
         return distance;
     }

     public baseStation getBs() {
         return bs;
     }

     public static int getN() {
         return n;
     }

     public int getId() {
         return id;
     }

     public int getY() {
         return y;
     }

     myPhone(int x, int y){
        setPhoneNumber();
        this.x=x;
        this.y=y;
        this.distance= Math.pow(baseStation.R*2,2)+1;
        bs=null;
        id=n++;
    }
    void setPhoneNumber(){//initiate phone number
        for(int i=0;i<8;i++){
            this.phoneNumber.append(new Random().nextInt(10));
        }
    }
    void randomMove(){
         x=new Random().nextInt(30);
         y=new Random().nextInt(30);
    }

}
class baseStation{
     static final int R = 15;
    static int n=1;
    int id;

    public static int getR() {
        return R;
    }

    public static int getN() {
        return n;
    }

    public int getId() {
        return id;
    }

    public ArrayList<myPhone> getPhones() {
        return phones;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private  int x,y;
    ArrayList<myPhone> phones = new ArrayList<>();//includes phones near the station
    baseStation(int x,int y){
        this.x=x;
        this.y=y;
        id=n++;
    }
}
public class xdu {

    public static void main(String[] args) {
        myPhone mp1= new myPhone(new Random().nextInt(30),new Random().nextInt(30));
        myPhone mp2= new myPhone(new Random().nextInt(30),new Random().nextInt(30));
        myPhone mp3= new myPhone(new Random().nextInt(30),new Random().nextInt(30));
        myPhone mp4= new myPhone(new Random().nextInt(30),new Random().nextInt(30));
        ArrayList<myPhone> phones= new ArrayList<>();
        phones.add(mp1);
        phones.add(mp2);
        phones.add(mp3);
        phones.add(mp4);

        baseStation bs1=new baseStation(0,0);
        baseStation bs2=new baseStation(0,30);
        baseStation bs3=new baseStation(30,0);
        baseStation bs4=new baseStation(30,30);
        baseStation bs5=new baseStation(15,15);
        ArrayList<baseStation> baseStations= new ArrayList<>();
        baseStations.add(bs1);
        baseStations.add(bs2);
        baseStations.add(bs3);
        baseStations.add(bs4);
        baseStations.add(bs5);

       for(myPhone tem:phones){
           int x=tem.getX();
           int y=tem.getY();
           for(baseStation baseStation:baseStations){
                int b_x=baseStation.getX();
                int b_y=baseStation.getY();
                double distance=Math.abs((x-b_x)*(x-b_x)+(y-b_y)*(y-b_y));
                if(distance<tem.distance){
                    tem.distance=distance;
                    tem.bs=baseStation;
                }
           }
           tem.getBs().getPhones().add(tem);
       }

       for(myPhone myPhone:phones){
           System.out.println(myPhone.getId()+"号手机当前位置为:x:"+myPhone.getX()+" y:"+myPhone.getY()+"可以与"+myPhone.getBs().id+"号基站通讯");
       }
        for(baseStation baseStation:baseStations){
            System.out.print("基站"+baseStation.getId()+"可与手机通信:");
            for(myPhone myPhone:baseStation.getPhones()){
                System.out.print(myPhone.getId());
            }
            System.out.println("");
        }

       //e.g
       //the first phone communicate with the second
        // the third phone communicate with the forth
        for(baseStation baseStation:baseStations){
            System.out.println("处在基站"+mp1.getBs().id+"的手机1"+mp1.getPhoneNumber()+"正在呼叫手机2"+mp2.getPhoneNumber());
            if(baseStation.getPhones().contains(mp2)){
                System.out.println("找到被呼叫手机所在基站"+baseStation.getId());
                System.out.println("正在与被呼叫手机建立通讯......");
                System.out.println("建立成功！");
                break;
            }else{
                System.out.println("被呼叫的手机不在基站"+baseStation.id+"中");
            }
        }

        for(baseStation baseStation:baseStations){
            System.out.println("处在基站"+mp3.getBs().id+"的手机3"+mp3.getPhoneNumber()+"正在呼叫手机4"+mp4.getPhoneNumber());
            if(baseStation.getPhones().contains(mp3)){
                System.out.println("找到被呼叫手机所在基站"+baseStation.getId());
                System.out.println("正在与被呼叫手机建立通讯......");
                System.out.println("建立成功！");
                break;
            }else{
                System.out.println("被呼叫的手机不在基站"+baseStation.id+"中");
            }
        }


        System.out.println("手机随机移动.......");

        for(myPhone myPhone:phones){
            myPhone.randomMove();
        }
        System.out.println("手机添加....");


        for(baseStation baseStation:baseStations){
            baseStation.getPhones().clear();
        }

        myPhone mp5= new myPhone(new Random().nextInt(30),new Random().nextInt(30));
        myPhone mp6= new myPhone(new Random().nextInt(30),new Random().nextInt(30));
        phones.add(mp5);
        phones.add(mp6);


        for(myPhone tem:phones){
            int x=tem.getX();
            int y=tem.getY();
            for(baseStation baseStation:baseStations){
                int b_x=baseStation.getX();
                int b_y=baseStation.getY();
                double distance=Math.abs((x-b_x)*(x-b_x)+(y-b_y)*(y-b_y));
                if(distance<tem.distance){
                    tem.distance=distance;
                    tem.bs=baseStation;
                }
            }
            tem.getBs().getPhones().add(tem);
        }


        for(baseStation baseStation:baseStations){
            System.out.print("基站"+baseStation.getId()+"可与手机通信:");
            for(myPhone myPhone:baseStation.getPhones()){
                System.out.print(myPhone.getId());
            }
            System.out.println("");
        }


        for(myPhone myPhone:phones){
            System.out.println(myPhone.getId()+"号手机当前位置为:x:"+myPhone.getX()+" y:"+myPhone.getY()+"可以与"+myPhone.getBs().id+"号基站通讯");
        }
        //e.g
        //the first phone communicate with the second
        // the third phone communicate with the forth
        for(baseStation baseStation:baseStations){
            System.out.println("处在基站"+mp1.getBs().id+"的手机1:"+mp1.getPhoneNumber()+"正在呼叫手机2:"+mp2.getPhoneNumber());
            if(baseStation.getPhones().contains(mp2)){
                System.out.println("找到被呼叫手机所在基站"+baseStation.getId());
                System.out.println("正在与被呼叫手机建立通讯......");
                System.out.println("建立成功！");
                break;
            }else{
                System.out.println("被呼叫的手机不在基站"+baseStation.id+"中");
            }
        }

        for(baseStation baseStation:baseStations){
            System.out.println("处在基站"+mp3.getBs().id+"的手机3:"+mp3.getPhoneNumber()+"正在呼叫手机4:"+mp4.getPhoneNumber());
            if(baseStation.getPhones().contains(mp3)){
                System.out.println("找到被呼叫手机所在基站"+baseStation.getId());
                System.out.println("正在与被呼叫手机建立通讯......");
                System.out.println("建立成功！");
                break;
            }else{
                System.out.println("被呼叫的手机不在基站"+baseStation.id+"中");
            }
        }

        for(baseStation baseStation:baseStations){
            System.out.println("处在基站"+mp5.getBs().id+"的手机5:"+mp5.getPhoneNumber()+"正在呼叫手机6:"+mp6.getPhoneNumber());
            if(baseStation.getPhones().contains(mp5)){
                System.out.println("找到被呼叫手机所在基站"+baseStation.getId());
                System.out.println("正在与被呼叫手机建立通讯......");
                System.out.println("建立成功！");
                break;
            }else{
                System.out.println("被呼叫的手机不在基站"+baseStation.id+"中");
            }
        }







    }
}