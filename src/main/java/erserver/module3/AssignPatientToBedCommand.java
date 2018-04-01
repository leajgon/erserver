package erserver.module3;

import erserver.module2.Patient;
import erserver.module3.Bed;
import erserver.module3.InboundPatientController;
import erserver.module3.StaffAssignmentManager;

public class AssignPatientToBedCommand {

   private StaffAssignmentManager staffAssignmentManager;
   private InboundPatientController inboundPatientController;

   public AssignPatientToBedCommand(StaffAssignmentManager staffAssignmentManager, InboundPatientController inboundPatientController) {
      this.staffAssignmentManager = staffAssignmentManager;
      this.inboundPatientController = inboundPatientController;
   }

   public void assignPatientToBed(int transportId, int bedId) {
      Bed bed = staffAssignmentManager.getBedById(bedId);
      Patient patient = getPatientByTransport(transportId);
      staffAssignmentManager.assignPatientToBed(patient, bed);
      inboundPatientController.informOfPatientArrival(transportId);
   }

   private Patient getPatientByTransport(int transportId) {
      for (Patient patient : inboundPatientController.currentInboundPatients()) {
         if (patient.getTransportId() == transportId) {
            return patient;
         }
      }
      throw new RuntimeException("Unable to find inbound patient " + transportId);
   }


}
