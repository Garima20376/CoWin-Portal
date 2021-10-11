import java.util.*;



class Citizen{
    private String U_id;
    private String name;
    private int age;
    VaccStatus VS;
    //builtin Arraylist data structure used so that the as the citizens
    //are being registered, they can keep being added to the portal to
    //store their data
    static ArrayList<Citizen> CitRecs = new ArrayList<Citizen>();

    public Citizen(){
        VS = new VaccStatus("REGISTERED", "None",0);
    }

    public String getU_id() {
        return U_id;
    }
    public String getName() {
        return name;
    }

    public static boolean CheckID(String id){
        if(id.length()!=12) {
            System.out.println("Unique ID should be 12 digits exactly!");
            return false;
        }
        return true;
    }
    public int getDue(){
        return VS.getDue_day();
    }
    public void updateVS(String name, int day) {
        VS.setVaccineGiven(name);
        VS.updateStatus(day);
    }

    public void addCitizen(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Citizen Name: ");
        name = scn.nextLine();
        System.out.print("Age: ");
        age = Integer.parseInt(scn.nextLine());
        System.out.print("Unique ID: ");
        U_id = scn.nextLine();
    }

    public boolean toRegister(){
        boolean flag1 = false;
        boolean flag2 = true;
        boolean flag3 = true;

        if (!Citizen.CheckID(U_id))
            flag3 = false;

        if (age>=18){
            flag1 = true;
        }
        else{
            System.out.println("Only citizens above 18 years can register!");
        }


        for (int i = 0; i < CitRecs.size(); i++) {
            if (CitRecs.get(i).getU_id().equals(this.U_id)) {
                System.out.println("Unique ID already registered!");
                flag2 = false;
                break;
            }
        }


        if (flag1 && flag2 && flag3){
            CitRecs.add(this);
            System.out.println("Citizen Registered!");
        }
        return flag1 && flag2;
    }

    public void displayCitizen(){
        System.out.println("\nCitizen Name: "+ name + ", Age: " + age + ", Unique ID: "+U_id);
    }


}


class Hospital{
    //list of slots for this hospital
    private ArrayList<Slot> listofSlots;
    private int U_id;
    private String name;
    private String PinCode;
    static int id_counter = 0;
    //Record of Hospitals registered
    static ArrayList<Hospital> HospRecs = new ArrayList<Hospital>();

    public Hospital(){
        this.U_id = ++id_counter;
        listofSlots = new ArrayList<Slot>();
    }

    public static boolean CheckID(String id){
        if(id.length()!=6) {
            System.out.println("Unique ID should be 6 digits exactly!");
            return false;
        }
        return true;
    }

    void createSlot(Slot slot){
        listofSlots.add(slot);
    }

    void regHospital(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Hospital name: ");
        name = scn.nextLine();
        System.out.print("PinCode : ");
        PinCode = scn.nextLine();
        HospRecs.add(this);
    }

    void displayHospital(){
        System.out.println("Hospital Name: "+ name + ", PinCode: " + PinCode + ", Unique ID: "+String. format("%06d", U_id));
    }

    void displaySlots(){
        int j=0;
        for(int i=0; i<listofSlots.size(); i++){
            if (listofSlots.get(i).getQty()!=0) {
                j++;
                System.out.print(i + 1 + ". ");
                listofSlots.get(i).displaySlot(/*U_id, listofSlots.get(i).getVaccName()*/);
            }
        }
        if (j==0){
            System.out.print("No Slots Available");
        }
    }

    public int getU_id() {
        return U_id;
    }
    public String getPinCode() {
        return PinCode;
    }
    public String getName() {
        return name;
    }

    public ArrayList<Slot> getListofSlots() {
        return listofSlots;
    }
}



class Vaccine{
    private String name;
    private int no_of_doses;
    private int gap;
    //Vaccines available on the portal
    static ArrayList<Vaccine> VaccRecs = new ArrayList<Vaccine>();

    public int getNo_of_doses() { return no_of_doses; }

    public int getGap() { return gap; }

    public String getName() {
        return name;
    }

    void addVaccine(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Vaccine name: ");
        this.name = scn.nextLine();
        System.out.print("No. of doses: ");
        this.no_of_doses = Integer.parseInt(scn.nextLine());
        System.out.print("Gap between doses: ");
        this.gap = Integer.parseInt(scn.nextLine());
        VaccRecs.add(this);
    }

    static void dispListOfVacc(){
        for(int i=0; i<VaccRecs.size(); i++){
            System.out.println((i+1)+". "+VaccRecs.get(i).getName());
        }
    }
    void displayVaccine(){
        System.out.println("\nVaccine Name: "+ this.name + ", No.of doses: " + this.no_of_doses + ", Gap b/w doses: "+this.gap);
    }
}


class SlotBooker{

    static ArrayList<Hospital> listofHosp = Hospital.HospRecs;
    static ArrayList<Vaccine> listofVacc = Vaccine.VaccRecs;


    void byPincode(String PinCode){
        for (int i=0; i< listofHosp.size();i++){
            if (listofHosp.get(i).getPinCode().equals(PinCode)){
                System.out.println(String. format("%06d", listofHosp.get(i).getU_id())+" "+listofHosp.get(i).getName());
            }
        }
    }
    void byVaccine(String VaccName){
        for (int i=0; i< listofVacc.size();i++){
            if (listofVacc.get(i).getName().equals(VaccName)){
                System.out.println(String.format("%06d",listofHosp.get(i).getU_id())+" "+listofHosp.get(i).getName());
            }
        }
    }
    boolean displaySlots(int HospID){
        int j=0;
        for(int i=0; i<listofHosp.get(HospID-1).getListofSlots().size(); i++){
            if (listofHosp.get(HospID-1).getListofSlots().get(i).getQty()!=0) {
                j++;
                System.out.print(i + 1 + "-> ");
                listofHosp.get(HospID - 1).getListofSlots().get(i).displaySlot(/*HospID, listofHosp.get(HospID - 1).getListofSlots().get(i).getVaccName()*/);
            }
        }
        if (j==0){
            System.out.print("No Slots Available");
            return false;
        }
        return true;
    }

    boolean displaySlots(int HospID, String VaccName){
        int j=0;
        for(int i=0; i<listofHosp.get(HospID-1).getListofSlots().size(); i++){
            if (listofHosp.get(HospID-1).getListofSlots().get(i).getQty()!=0 && listofHosp.get(HospID-1).getListofSlots().get(i).getVaccName().equals(VaccName)) {
                j++;
                System.out.print(i + 1 + "-> ");
                listofHosp.get(HospID - 1).getListofSlots().get(i).displaySlot(/*HospID, listofHosp.get(HospID - 1).getListofSlots().get(i).getVaccName()*/);
            }
        }
        if (j==0){
            System.out.print("No Slots Available");
            return false;
        }
        return true;
    }



    void Booked(Citizen C, String VaccName){
        System.out.println(C.getName()+" vaccinated with "+ VaccName);
    }
}




class Slot{
    //private Hospital hospital;
    private int day;
    private int qty;
    private String VaccName;
    //list of all slots created
    //static ArrayList<Slot> Slots = new ArrayList<Slot>();

    /*public Slot(Hospital hospital){
        hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }*/

    public int getDay() {
        return day;
    }
    public String getVaccName() {
        return VaccName;
    }
    public int getQty() {
        return qty;
    }

    public void addSlot(int day, int qty, String name){
        this.day = day;
        this.qty = qty;
        this.VaccName = name;
    }

    void decQty(){
        qty--;
    }

    public void displaySlot(int id, String VaccName){
        System.out.println("Slot added by Hospital "+String.format("%06d", id)+ " for day "+day+", Available Quantity: "+qty+" of Vaccine "+VaccName);
    }
    public void displaySlot(){
        System.out.println( "Day "+day+", Available Quantity: "+qty+" of Vaccine "+VaccName);
    }
}


class VaccStatus{
    private String Status;
    private String VaccineGiven;
    private int DosesGiven;
    private int due_day;
    Vaccine VaccInfo;

    public VaccStatus(String Status, String VaccineGiven, int DosesGiven){
        due_day = 0;
        this.Status = Status;
        this.VaccineGiven = VaccineGiven;
        this.DosesGiven = DosesGiven;
    }

    public int getDue_day() {
        return due_day;
    }

    public void setVaccineGiven(String vaccineGiven) {
        VaccineGiven = vaccineGiven;
        getVaccInfo();
    }

    void getVaccInfo(){
        for(int i=0; i<Vaccine.VaccRecs.size();i++){
            if(Vaccine.VaccRecs.get(i).getName().equals(VaccineGiven)){
                VaccInfo = Vaccine.VaccRecs.get(i);
                break;
            }
        }
    }

    void updateStatus(int day/*, String VaccName*/){
        DosesGiven++;
        if (DosesGiven<VaccInfo.getNo_of_doses()){
            Status = "PARTIALLY VACCINATED";
        }
        else{
            Status = "FULLY VACCINATED";
        }
        if(Status == "PARTIALLY VACCINATED"){
            due_day = day + VaccInfo.getGap();
        }
    }

    void display(){
        System.out.println(Status+"\nVaccine Given: "+VaccineGiven+"\nNumber of Doses Given: "+ DosesGiven);
        if(Status == "PARTIALLY VACCINATED"){
            System.out.println("Due Day: "+due_day);
        }
    }
}



public class CoWin_app {



    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        String MenuOptions = "1.Add Vaccine \n2.Register Hospital \n3.Register Citizen \n4.Add Slot for Vaccination \n5.Book Slot for Vaccination \n6.List all slots of a hospital \n7.Check Vaccination Status \n8.Exit";
        String bar = "------------------------------\n";

        System.out.println("CoWin Portal Initialised....");
        System.out.println(bar);

        int ch,flag;

        while(true) {
            System.out.println(bar);
            System.out.println(MenuOptions);
            System.out.println(bar);

            System.out.print("Enter Option No.: ");
            ch = Integer.parseInt(scn.nextLine());
            flag = 1;
            switch (ch) {

                case 1:
                    Vaccine V = new Vaccine();
                    V.addVaccine();
                    System.out.println("Vaccine Added!");
                    V.displayVaccine();
                    System.out.println(bar);
                    break;

                case 2:
                    Hospital H = new Hospital();
                    H.regHospital();
                    System.out.println("Hospital  Registered!");
                    H.displayHospital();
                    System.out.println(bar);
                    break;

                case 3:
                    Citizen C = new Citizen();
                    C.addCitizen();
                    if (C.toRegister())
                        C.displayCitizen();
                    System.out.println(bar);
                    break;

                case 4:
                    int n,day,qty,choice,id;
                    String Vaccname,id_s;
                    System.out.print("Enter Hospital ID: ");
                    id_s = scn.nextLine();
                    if(!Hospital.CheckID(id_s)){
                        break;
                    }
                    id = Integer.parseInt(id_s);
                    System.out.print("Enter number of Slots to be added: ");
                    n = Integer.parseInt(scn.nextLine());

                    for(int i=0;i<n;i++){
                        Slot S = new Slot();
                        System.out.print("Enter Day Number: ");
                        day = Integer.parseInt(scn.nextLine());
                        System.out.print("Enter Quantity: ");
                        qty = Integer.parseInt(scn.nextLine());
                        System.out.println("Select Option: ");
                        Vaccine.dispListOfVacc();
                        ch = Integer.parseInt(scn.nextLine());
                        S.addSlot(day,qty,Vaccine.VaccRecs.get(ch-1).getName());
                        Hospital.HospRecs.get(id-1).createSlot(S);
                        S.displaySlot(id, Vaccine.VaccRecs.get(ch-1).getName());
                    }
                    break;
                case 5:
                    id = 1;
                    String Uid, pin, vacc;
                    int slot, CitIndex = -1;
                    System.out.print("Enter patient Unique ID: ");
                    Uid = scn.nextLine();
                    if(!Citizen.CheckID(Uid))
                        break;
                    for(int i=0; i<Citizen.CitRecs.size(); i++){
                        if (Citizen.CitRecs.get(i).getU_id().equals(Uid)){
                            CitIndex = i;
                            break;
                        }
                    }
                    if(CitIndex == -1){
                        System.out.println("Citizen not registered!");
                    }
                    else {
                        System.out.print("Select Option: \n1. Search by Area\n2. Search by Vaccine\nEnter Option: ");
                        choice = Integer.parseInt(scn.nextLine());

                        SlotBooker SB = new SlotBooker();
                        boolean f = false;
                        if (choice == 1) {
                            System.out.print("Enter PinCode: ");
                            pin = scn.nextLine();
                            SB.byPincode(pin);
                            System.out.print("Enter Hospital ID: ");
                            id_s = scn.nextLine();
                            if(!Hospital.CheckID(id_s))
                                break;
                            id = Integer.parseInt(id_s);
                            f = SB.displaySlots(id);
                        } else if (choice == 2) {
                            System.out.print("Enter Vaccine Name: ");
                            vacc = scn.nextLine();
                            SB.byVaccine(vacc);
                            System.out.print("Enter Hospital ID: ");
                            id_s = scn.nextLine();
                            if(!Hospital.CheckID(id_s))
                                break;
                            id = Integer.parseInt(id_s);
                            f = SB.displaySlots(id,vacc);
                        }
                        if (f) {
                            System.out.print("Choose Slot: ");
                            slot = Integer.parseInt(scn.nextLine());
                            if (Citizen.CitRecs.get(CitIndex).getDue()!=0 && (Hospital.HospRecs.get(id-1).getListofSlots().get(slot-1).getDay() < Citizen.CitRecs.get(CitIndex).getDue())){
                                System.out.println("Can't book a slot before due date!!");
                                break;
                            }
                            SB.Booked(Citizen.CitRecs.get(CitIndex), Hospital.HospRecs.get(id-1).getListofSlots().get(slot-1).getVaccName());
                            Hospital.HospRecs.get(id-1).getListofSlots().get(slot-1).decQty();
                            Citizen.CitRecs.get(CitIndex).updateVS(Hospital.HospRecs.get(id-1).getListofSlots().get(slot-1).getVaccName(),Hospital.HospRecs.get(id-1).getListofSlots().get(slot-1).getDay());
                        }

                    }
                    break;
                case 6:
                    System.out.print("Enter Hospital ID: ");

                    id_s = scn.nextLine();
                    if(!Hospital.CheckID(id_s))
                        break;

                    id = Integer.parseInt(id_s);
                    Hospital.HospRecs.get(id-1).displaySlots();
                    break;
                case 7:
                    CitIndex = -1;
                    System.out.print("Enter patient Unique ID: ");
                    Uid = scn.nextLine();
                    if(!Citizen.CheckID(Uid))
                        break;

                    for(int i=0; i<Citizen.CitRecs.size(); i++){
                        if (Citizen.CitRecs.get(i).getU_id().equals(Uid)){
                            CitIndex = i;
                            break;
                        }
                    }
                    if(CitIndex == -1){
                        System.out.println("Citizen not registered!");
                    }
                    else{
                        Citizen.CitRecs.get(CitIndex).VS.display();
                    }

                    break;
                case 8:
                    flag = 0;
                    break;

                default:
                    System.out.println("Enter valid option!");
            }
            if (flag==0){
                break;
            }
        }

        System.out.println("Thank You For Visiting!");
    }
}











