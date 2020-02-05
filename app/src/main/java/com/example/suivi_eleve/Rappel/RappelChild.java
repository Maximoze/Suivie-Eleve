package com.example.suivi_eleve.Rappel;

    public class RappelChild  {

        public String present;
        public String absent;

        public RappelChild(String present, String absent) {
            this.present = present;
            this.absent = absent;
        }

        public String getPresent() {
            return present;
        }

        public void setPresent(String present) {
            this.present = present;
        }

        public String getAbsent() {
            return absent;
        }

        public void setAbsent(String absent) {
            this.absent = absent;
        }
    }
