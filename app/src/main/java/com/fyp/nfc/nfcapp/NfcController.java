package com.fyp.nfc.nfcapp;


import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

public class NfcController {

    private Tag cTag;

    public NfcController(Tag tag) {
        this.cTag = tag;
    }

    public boolean writeMsg (String msgString) {
        Ndef ndef = Ndef.get(cTag);

        if (ndef == null) {
            // NDEF is not supported by this Tag.
            return false;
        }

        Locale locale = Locale.US;
        String payload = msgString;

        try {
            ndef.connect();
            if(ndef.isConnected() && ndef.isWritable()) {
                NdefRecord records = createTextRecord(payload, locale, true);
                NdefMessage message = new NdefMessage(records);
                Log.v("check", message.toString());
                if(ndef.getMaxSize() > message.getByteArrayLength()) {
                    ndef.writeNdefMessage(message);
                }
            }

            ndef.close();
            return true;
        }
        catch (Exception e) {
            //do error handling
            return false;
        }
    }

    public String readMsg () {

        return null;
    }

    public NdefRecord createTextRecord(String payload, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
        byte[] textBytes = payload.getBytes(utfEncoding);
        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char) (utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);
        return record;
    }

    private String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

        byte[] payload = record.getPayload();

        // Get the Text Encoding
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

        // Get the Language Code
        int languageCodeLength = payload[0] & 0063;

        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
        // e.g. "en"

        // Get the Text
        return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
    }
}
