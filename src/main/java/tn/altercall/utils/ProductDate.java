package tn.altercall.utils;

import java.util.ArrayList;

public class ProductDate {

        public ArrayList<String> doneSp;
        public ArrayList<String> newSp ;
        public ArrayList<String > remainingSp ;


        public ProductDate(ArrayList<String> doneSp, ArrayList<String> newSp, ArrayList<String> remainingSp) {
            this.remainingSp = remainingSp;
            this.doneSp = doneSp;
            this.newSp = newSp;
        }


}
