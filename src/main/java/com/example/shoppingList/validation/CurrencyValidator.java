package com.example.shoppingList.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<CurrencyValid,String> {
    static Set<String> currencies = new HashSet<>();
    
    static {
        currencies.add("AED");
        currencies.add("AFN");
        currencies.add("ALL");
        currencies.add("AMD");
        currencies.add("ANG");
        currencies.add("AOA");
        currencies.add("ARS");
        currencies.add("AUD");
        currencies.add("AWG");
        currencies.add("AZN");
        currencies.add("BAM");
        currencies.add("BBD");
        currencies.add("BDT");
        currencies.add("BGN");
        currencies.add("BHD");
        currencies.add("BIF");
        currencies.add("BMD");
        currencies.add("BND");
        currencies.add("BOB");
        currencies.add("BRL");
        currencies.add("BSD");
        currencies.add("BTN");
        currencies.add("BWP");
        currencies.add("BYR");
        currencies.add("BZD");
        currencies.add("CAD");
        currencies.add("CDF");
        currencies.add("CHF");
        currencies.add("CKD");
        currencies.add("CLP");
        currencies.add("CNY");
        currencies.add("COP");
        currencies.add("CRC");
        currencies.add("CUP");
        currencies.add("CVE");
        currencies.add("CZK");
        currencies.add("DJF");
        currencies.add("DKK");
        currencies.add("DOP");
        currencies.add("DZD");
        currencies.add("EGP");
        currencies.add("ERN");
        currencies.add("ETB");
        currencies.add("FJD");
        currencies.add("FKP");
        currencies.add("FOK");
        currencies.add("GBP");
        currencies.add("GEL");
        currencies.add("GGP");
        currencies.add("GHS");
        currencies.add("GIP");
        currencies.add("GMD");
        currencies.add("GNF");
        currencies.add("GTQ");
        currencies.add("GYD");
        currencies.add("HKD");
        currencies.add("HNL");
        currencies.add("HRK");
        currencies.add("HTG");
        currencies.add("HUF");
        currencies.add("IDR");
        currencies.add("ILS");
        currencies.add("IMP");
        currencies.add("INR");
        currencies.add("IQD");
        currencies.add("IRR");
        currencies.add("ISK");
        currencies.add("JEP");
        currencies.add("JMD");
        currencies.add("JOD");
        currencies.add("JPY");
        currencies.add("KES");
        currencies.add("KGS");
        currencies.add("KHR");
        currencies.add("KID");
        currencies.add("KMF");
        currencies.add("KPW");
        currencies.add("KRW");
        currencies.add("KWD");
        currencies.add("KYD");
        currencies.add("KZT");
        currencies.add("LAK");
        currencies.add("LBP");
        currencies.add("LKR");
        currencies.add("LRD");
        currencies.add("LSL");
        currencies.add("LYD");
        currencies.add("MAD");
        currencies.add("MDL");
        currencies.add("MGA");
        currencies.add("MKD");
        currencies.add("MMK");
        currencies.add("MNT");
        currencies.add("MOP");
        currencies.add("MRO");
        currencies.add("MUR");
        currencies.add("MVR");
        currencies.add("MWK");
        currencies.add("MXN");
        currencies.add("MYR");
        currencies.add("MZN");
        currencies.add("NAD");
        currencies.add("NGN");
        currencies.add("NIO");
        currencies.add("NOK");
        currencies.add("NPR");
        currencies.add("NZD");
        currencies.add("OMR");
        currencies.add("PAB");
        currencies.add("PEN");
        currencies.add("PGK");
        currencies.add("PHP");
        currencies.add("PKR");
        currencies.add("PLN");
        currencies.add("PYG");
        currencies.add("QAR");
        currencies.add("RON");
        currencies.add("RSD");
        currencies.add("RUB");
        currencies.add("RWF");
        currencies.add("SAR");
        currencies.add("SBD");
        currencies.add("SCR");
        currencies.add("SDG");
        currencies.add("SEK");
        currencies.add("SGD");
        currencies.add("SHP");
        currencies.add("SLL");
        currencies.add("SOS");
        currencies.add("SRD");
        currencies.add("SSP");
        currencies.add("STD");
        currencies.add("SYP");
        currencies.add("SZL");
        currencies.add("THB");
        currencies.add("TJS");
        currencies.add("TMT");
        currencies.add("TND");
        currencies.add("TOP");
        currencies.add("TRY");
        currencies.add("TTD");
        currencies.add("TVD");
        currencies.add("TWD");
        currencies.add("TZS");
        currencies.add("UAH");
        currencies.add("UGX");
        currencies.add("USD");
        currencies.add("UYU");
        currencies.add("UZS");
        currencies.add("VND");
        currencies.add("VUV");
        currencies.add("WST");
        currencies.add("XAF");
        currencies.add("XCD");
        currencies.add("XOF");
        currencies.add("XPF");
        currencies.add("YER");
        currencies.add("ZAR");
        currencies.add("ZMW");
        currencies.add("ZWL");
    }
    @Override
    public boolean isValid(String currency, ConstraintValidatorContext context) {

        if (currency == null) {
            return true;
        }

        return currencies.contains(currency);
    }
}
