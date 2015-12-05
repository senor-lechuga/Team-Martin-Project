import java.util.*;

public class Calendar {
  private ArrayList<Appointments> appointments = new ArrayList<Appointments>();
  public Calendar (ArrayList<Appointments> apps) {
      appointments = apps;
  }

  public ArrayList<Appointments> getAppointments(){
      return appointments;
  }

  public void addAppointment(Appointments a){
      int size = appointments.size();
      for(Appointments apps : appointments){
          if(a.getEndTime().before(apps.getStartTime())
              || a.getStartTime().after(apps.getEndTime())
                  && a.getPartner() == apps.getPartner()){
                      appointments.add(a);
                      break;
                  }
      }
      if(appointments.size()>size)
          System.out.print("Appointment has been added succesfully");
      else
          System.out.print("Appointment cannot be added to the calendar");
  }

  public ArrayList<Appointments> getDentistAppointments(){
    ArrayList<Appointments> apps = new ArrayList<Appointments>();
    for(Appointments a : apps){
      if (a.getPartner() == "Dentist")
          apps.add(a);
    }
    return apps;
  }

  public ArrayList<Appointments> getHygienistAppointments(){
    ArrayList<Appointments> apps = new ArrayList<Appointments>();
    for(Appointments a : apps){
      if (a.getPartner() == "Hygienist")
          apps.add(a);
    }
    return apps;
  }
}
