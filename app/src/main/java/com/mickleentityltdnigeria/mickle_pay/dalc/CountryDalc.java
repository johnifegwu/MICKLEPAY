package com.mickleentityltdnigeria.mickle_pay.dalc;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.CountryEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.Country;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryDalc {

    private FirebaseDatabase database;
    private final DatabaseReference countryDB;
    private final CountryEvents countryEvents;

    private static int _ID = 0;


    public CountryDalc(CountryEvents countryEvents) {
        this.countryEvents = countryEvents;
        this.database = FirebaseDatabase.getInstance();
        this.countryDB = database.getReference(DBReferences.COUNTRIES());
    }

    private static int genID(){
        _ID = _ID + 1;
        return _ID;
    }

    private void AddCountry(){
        List<Country> countries = getMemCountries();
        //
        for(Country c:countries){
            //save countries to the system.
            countryDB.child(String.valueOf(c.countryID)).setValue(c);
        }
        //raise event
        countryEvents.onCountriesUpdated(countries);
    }

    public static List<Country> getMemCountries(){
        List<Country> countries = new ArrayList<>();
        //We will populate countries here:
        countries.add( new Country(genID(),"971","93","AFN","AFGHANISTAN"));
        countries.add( new Country(genID(),"008","355","ALL","ALBANIA"));
        countries.add( new Country(genID(),"012","213","DZD","ALGERIA"));
        countries.add( new Country(genID(),"840","1","USD","AMERICAN SAMOA"));
        countries.add( new Country(genID(),"978","376","EUR","ANDORRA"));
        countries.add( new Country(genID(),"973","244","AOA","ANGOLA"));
        countries.add( new Country(genID(),"951","1","XCD","ANGUILLA"));
        countries.add( new Country(genID(),"951","1","XCD","ANTIGUA AND BARBUDA"));
        countries.add( new Country(genID(),"032","54","ARS","ARGENTINA"));
        countries.add( new Country(genID(),"051","374","AMD","ARMENIA"));
        countries.add( new Country(genID(),"533","297","AWG","ARUBA"));
        countries.add( new Country(genID(),"036","61","AUD","AUSTRALIA"));
        countries.add( new Country(genID(),"978","43","EUR","AUSTRIA"));
        countries.add( new Country(genID(),"944","994","AZN","AZERBAIJAN"));
        countries.add( new Country(genID(),"044","1","BSD","BAHAMAS"));
        countries.add( new Country(genID(),"048","973","BHD","BAHRAIN"));
        countries.add( new Country(genID(),"050","880","BDT","BANGLADESH"));
        countries.add( new Country(genID(),"052","1","BBD","BARBADOS"));
        countries.add( new Country(genID(),"933","375","BYN","BELARUS"));
        countries.add( new Country(genID(),"978","32","EUR","BELGIUM"));
        countries.add( new Country(genID(),"084","501","BZD","BELIZE"));
        countries.add( new Country(genID(),"952","229","XOF","BENIN"));
        countries.add( new Country(genID(),"060","1","BMD","BERMUDA"));
        countries.add( new Country(genID(),"064","975","BTN","BHUTAN"));
        countries.add( new Country(genID(),"068","591","BOB","BOLIVIA"));
        countries.add( new Country(genID(),"840","599","USD","BONAIRE, SINT EUSTATIUS AND SABA"));
        countries.add( new Country(genID(),"977","387","BAM","BOSNIA AND HERZEGOVINA"));
        countries.add( new Country(genID(),"072","267","BWP","BOTSWANA"));
        countries.add( new Country(genID(),"578","55","NOK","BOUVET ISLAND"));
        countries.add( new Country(genID(),"986","55","BRL","BRAZIL"));
        countries.add( new Country(genID(),"840","1","USD","BRITISH INDIAN OCEAN TERRITORY"));
        countries.add( new Country(genID(),"096","673","BND","BRUNEI DARUSSALAM"));
        countries.add( new Country(genID(),"975","359","BGN","BULGARIA"));
        countries.add( new Country(genID(),"952","226","XOF","BURKINA FASO"));
        countries.add( new Country(genID(),"108","257","BIF","BURUNDI"));
        countries.add( new Country(genID(),"132","238","CVE","CAPE VERDE"));
        countries.add( new Country(genID(),"116","855","KHR","CAMBODIA"));
        countries.add( new Country(genID(),"950","237","XAF","CAMEROON"));
        countries.add( new Country(genID(),"124","1","CAD","CANADA"));
        countries.add( new Country(genID(),"136","1","KYD","CAYMAN ISLANDS"));
        countries.add( new Country(genID(),"950","236","XAF","CENTRAL AFRICAN REPUBLIC"));
        countries.add( new Country(genID(),"950","235","XAF","CHAD"));
        countries.add( new Country(genID(),"152","56","CLP","CHILE"));
        countries.add( new Country(genID(),"156","86","CNY","CHINA"));
        countries.add( new Country(genID(),"036","61","AUD","CHRISTMAS ISLAND"));
        countries.add( new Country(genID(),"036","61891","AUD","COCOS (KEELING) ISLANDS"));
        countries.add( new Country(genID(),"170","57","COP","COLOMBIA"));
        countries.add( new Country(genID(),"174","269","KMF","COMOROS"));
        countries.add( new Country(genID(),"976","243","CDF","CONGO (THE DEMOCRATIC REPUBLIC OF THE)"));
        countries.add( new Country(genID(),"950","242","XAF","CONGO (THE)"));
        countries.add( new Country(genID(),"554","682","NZD","COOK ISLANDS"));
        countries.add( new Country(genID(),"188","506","CRC","COSTA RICA"));
        countries.add( new Country(genID(),"191","385","HRK","CROATIA"));
        countries.add( new Country(genID(),"931","53","CUC","CUBA"));
        countries.add( new Country(genID(),"532","599","ANG","CURACAO"));
        countries.add( new Country(genID(),"978","357","EUR","CYPRUS"));
        countries.add( new Country(genID(),"203","420","CZK","CZECH REPUBLIC"));
        countries.add( new Country(genID(),"952","225","XOF","COTE D'IVOIRE"));
        countries.add( new Country(genID(),"208","45","DKK","DENMARK"));
        countries.add( new Country(genID(),"262","253","DJF","DJIBOUTI"));
        countries.add( new Country(genID(),"951","1","XCD","DOMINICA"));
        countries.add( new Country(genID(),"214","1","DOP","DOMINICAN REPUBLIC"));
        countries.add( new Country(genID(),"840","593","USD","ECUADOR"));
        countries.add( new Country(genID(),"818","20","EGP","EGYPT"));
        countries.add( new Country(genID(),"222","503","SVC","EL SALVADOR"));
        countries.add( new Country(genID(),"950","240","XAF","EQUATORIAL GUINEA"));
        countries.add( new Country(genID(),"232","291","ERN","ERITREA"));
        countries.add( new Country(genID(),"978","372","EUR","ESTONIA"));
        countries.add( new Country(genID(),"230","251","ETB","ETHIOPIA"));
        countries.add( new Country(genID(),"238","500","FKP","FALKLAND ISLANDS (MALVINAS)"));
        countries.add( new Country(genID(),"208","298","DKK","FAROE ISLANDS"));
        countries.add( new Country(genID(),"242","679","FJD","FIJI"));
        countries.add( new Country(genID(),"978","358","EUR","FINLAND"));
        countries.add( new Country(genID(),"978","33","EUR","FRANCE"));
        countries.add( new Country(genID(),"978","594","EUR","FRENCH GUIANA"));
        countries.add( new Country(genID(),"953","689","XPF","FRENCH POLYNESIA"));
        countries.add( new Country(genID(),"978","262","EUR","FRENCH SOUTHERN TERRITORIES"));
        countries.add( new Country(genID(),"950","241","XAF","GABON"));
        countries.add( new Country(genID(),"270","220","GMD","GAMBIA (THE)"));
        countries.add( new Country(genID(),"981","995","GEL","GEORGIA"));
        countries.add( new Country(genID(),"978","49","EUR","GERMANY"));
        countries.add( new Country(genID(),"936","233","GHS","GHANA"));
        countries.add( new Country(genID(),"292","350","GIP","GIBRALTAR"));
        countries.add( new Country(genID(),"978","30","EUR","GREECE"));
        countries.add( new Country(genID(),"208","299","DKK","GREENLAND"));
        countries.add( new Country(genID(),"951","1","XCD","GRENADA"));
        countries.add( new Country(genID(),"978","1","EUR","GUADELOUPE"));
        countries.add( new Country(genID(),"840","1","USD","GUAM"));
        countries.add( new Country(genID(),"320","502","GTQ","GUATEMALA"));
        countries.add( new Country(genID(),"826","01481","GBP","GUERNSEY"));
        countries.add( new Country(genID(),"324","224","GNF","GUINEA"));
        countries.add( new Country(genID(),"952","245","XOF","GUINEA-BISSAU"));
        countries.add( new Country(genID(),"328","592","GYD","GUYANA"));
        countries.add( new Country(genID(),"332","509","HTG","HAITI"));
        countries.add( new Country(genID(),"036","672","AUD","HEARD ISLAND AND McDONALD ISLANDS"));
        countries.add( new Country(genID(),"978","379","EUR","HOLY SEE (THE)"));
        countries.add( new Country(genID(),"340","504","HNL","HONDURAS"));
        countries.add( new Country(genID(),"344","852","HKD","HONG KONG"));
        countries.add( new Country(genID(),"348","36","HUF","HUNGARY"));
        countries.add( new Country(genID(),"352","354","ISK","ICELAND"));
        countries.add( new Country(genID(),"356","91","INR","INDIA"));
        countries.add( new Country(genID(),"360","62","IDR","INDONESIA"));
        countries.add( new Country(genID(),"364","98","IRR","IRAN (ISLAMIC REPUBLIC OF)"));
        countries.add( new Country(genID(),"368","964","IQD","IRAQ"));
        countries.add( new Country(genID(),"978","353","EUR","IRELAND"));
        countries.add( new Country(genID(),"826","44","GBP","ISLE OF MAN"));
        countries.add( new Country(genID(),"376","972","ILS","ISRAEL"));
        countries.add( new Country(genID(),"978","39","EUR","ITALY"));
        countries.add( new Country(genID(),"388","1","JMD","JAMAICA"));
        countries.add( new Country(genID(),"392","81","JPY","JAPAN"));
        countries.add( new Country(genID(),"826","44","GBP","JERSEY"));
        countries.add( new Country(genID(),"400","962","JOD","JORDAN"));
        countries.add( new Country(genID(),"398","7","KZT","KAZAKHSTAN"));
        countries.add( new Country(genID(),"404","254","KES","KENYA"));
        countries.add( new Country(genID(),"036","686","AUD","KIRIBATI"));
        countries.add( new Country(genID(),"408","850","KPW","KOREA (THE DEMOCRATIC PEOPLE’S REPUBLIC OF)"));
        countries.add( new Country(genID(),"410","82","KRW","KOREA (THE REPUBLIC OF)"));
        countries.add( new Country(genID(),"414","965","KWD","KUWAIT"));
        countries.add( new Country(genID(),"417","996","KGS","KYRGYZSTAN"));
        countries.add( new Country(genID(),"418","856","LAK","LAO PEOPLE’S DEMOCRATIC REPUBLIC (THE)"));
        countries.add( new Country(genID(),"978","371","EUR","LATVIA"));
        countries.add( new Country(genID(),"422","961","LBP","LEBANON"));
        countries.add(new Country(genID(), "426", "266", "LSL", "LESOTHO"));
        countries.add(new Country(genID(), "430", "231", "LRD", "LIBERIA"));
        countries.add(new Country(genID(), "434", "218", "LYD", "LIBYA"));
        countries.add(new Country(genID(), "756", "423", "CHF", "LIECHTENSTEIN"));
        countries.add(new Country(genID(), "978", "370", "EUR", "LITHUANIA"));
        countries.add(new Country(genID(), "978", "352", "EUR", "LUXEMBOURG"));
        countries.add(new Country(genID(), "446", "853", "MOP", "MACAO"));
        countries.add(new Country(genID(), "969", "261", "MGA", "MADAGASCAR"));
        countries.add(new Country(genID(), "454", "265", "MWK", "MALAWI"));
        countries.add(new Country(genID(), "458", "60", "MYR", "MALAYSIA"));
        countries.add(new Country(genID(), "462", "960", "MVR", "MALDIVES"));
        countries.add(new Country(genID(), "952", "223", "XOF", "MALI"));
        countries.add(new Country(genID(), "978", "356", "EUR", "MALTA"));
        countries.add(new Country(genID(), "840", "692", "USD", "MARSHALL ISLANDS (THE)"));
        countries.add(new Country(genID(), "978", "596", "EUR", "MARTINIQUE"));
        countries.add(new Country(genID(), "929", "222", "MRU", "MAURITANIA"));
        countries.add(new Country(genID(), "480", "230", "MUR", "MAURITIUS"));
        countries.add(new Country(genID(), "978", "262", "EUR", "MAYOTTE"));
        countries.add(new Country(genID(), "484", "52", "MXN", "MEXICO"));
        countries.add(new Country(genID(), "840", "691", "USD", "MICRONESIA (FEDERATED STATES OF)"));
        countries.add(new Country(genID(), "498", "373", "MDL", "MOLDOVA (THE REPUBLIC OF)"));
        countries.add(new Country(genID(), "978", "377", "EUR", "MONACO"));
        countries.add(new Country(genID(), "496", "976", "MNT", "MONGOLIA"));
        countries.add(new Country(genID(), "978", "382", "EUR", "MONTENEGRO"));
        countries.add(new Country(genID(), "951", "1", "XCD", "MONTSERRAT"));
        countries.add(new Country(genID(), "504", "212", "MAD", "MOROCCO"));
        countries.add(new Country(genID(), "943", "258", "MZN", "MOZAMBIQUE"));
        countries.add(new Country(genID(), "104", "95", "MMK", "MYANMAR"));
        countries.add(new Country(genID(), "516", "264", "NAD", "NAMIBIA"));
        countries.add(new Country(genID(), "036", "674", "AUD", "NAURU"));
        countries.add(new Country(genID(), "524", "977", "NPR", "NEPAL"));
        countries.add(new Country(genID(), "978", "31", "EUR", "NETHERLANDS (THE)"));
        countries.add(new Country(genID(), "953", "687", "XPF", "NEW CALEDONIA"));
        countries.add(new Country(genID(), "554", "64", "NZD", "NEW ZEALAND"));
        countries.add(new Country(genID(), "558", "505", "NIO", "NICARAGUA"));
        countries.add(new Country(genID(), "952", "227", "XOF", "NIGER (THE)"));
        countries.add(new Country(genID(), "566", "234", "NGN", "NIGERIA"));
        countries.add(new Country(genID(), "554", "683", "NZD", "NIUE"));
        countries.add(new Country(genID(), "036", "672", "AUD", "NORFOLK ISLAND"));
        countries.add(new Country(genID(), "840", "1", "USD", "NORTHERN MARIANA ISLANDS (THE)"));
        countries.add(new Country(genID(), "578", "47", "NOK", "NORWAY"));
        countries.add(new Country(genID(), "512", "968", "OMR", "OMAN"));
        countries.add(new Country(genID(), "586", "92", "PKR", "PAKISTAN"));
        countries.add(new Country(genID(), "840", "680", "USD", "PALAU"));
        countries.add(new Country(genID(), "590", "507", "PAB", "PANAMA"));
        countries.add(new Country(genID(), "598", "675", "PGK", "PAPUA NEW GUINEA"));
        countries.add(new Country(genID(), "600", "595", "PYG", "PARAGUAY"));
        countries.add(new Country(genID(), "604", "51", "PEN", "PERU"));
        countries.add(new Country(genID(), "608", "63", "PHP", "PHILIPPINES (THE)"));
        countries.add(new Country(genID(), "554", "870", "NZD", "PITCAIRN"));
        countries.add(new Country(genID(), "985", "48", "PLN", "POLAND"));
        countries.add(new Country(genID(), "978", "5", "EUR", "PORTUGAL"));
        countries.add(new Country(genID(), "840", "1", "USD", "PUERTO RICO"));
        countries.add(new Country(genID(), "634", "974", "QAR", "QATAR"));
        countries.add(new Country(genID(), "807", "389", "MKD", "REPUBLIC OF NORTH MACEDONIA"));
        countries.add(new Country(genID(), "946", "40", "RON", "ROMANIA"));
        countries.add(new Country(genID(), "643", "7", "RUB", "RUSSIAN FEDERATION (THE)"));
        countries.add(new Country(genID(), "646", "250", "RWF", "RWANDA"));
        countries.add(new Country(genID(), "978", "590", "EUR", "SAINT BARTH�LEMY"));
        countries.add(new Country(genID(), "654", "290", "SHP", "SAINT HELENA"));
        countries.add(new Country(genID(), "951", "1", "XCD", "SAINT KITTS AND NEVIS"));
        countries.add(new Country(genID(), "951", "1", "XCD", "SAINT LUCIA"));
        countries.add(new Country(genID(), "978", "590", "EUR", "SAINT MARTIN (FRENCH PART)"));
        countries.add(new Country(genID(), "978", "508", "EUR", "SAINT PIERRE AND MIQUELON"));
        countries.add(new Country(genID(), "951", "1", "XCD", "SAINT VINCENT AND THE GRENADINES"));
        countries.add(new Country(genID(), "882", "685", "WST", "SAMOA"));
        countries.add(new Country(genID(), "978", "378", "EUR", "SAN MARINO"));
        countries.add(new Country(genID(), "930", "239", "STN", "SAO TOME AND PRINCIPE"));
        countries.add(new Country(genID(), "682", "966", "SAR", "SAUDI ARABIA"));
        countries.add(new Country(genID(), "952", "221", "XOF", "SENEGAL"));
        countries.add(new Country(genID(), "941", "381", "RSD", "SERBIA"));
        countries.add(new Country(genID(), "690", "248", "SCR", "SEYCHELLES"));
        countries.add(new Country(genID(), "694", "232", "SLL", "SIERRA LEONE"));
        countries.add(new Country(genID(), "702", "65", "SGD", "SINGAPORE"));
        countries.add(new Country(genID(), "532", "590", "ANG", "SINT MAARTEN (DUTCH PART)"));
        countries.add(new Country(genID(), "978", "421", "EUR", "SLOVAKIA"));
        countries.add(new Country(genID(), "978", "386", "EUR", "SLOVENIA"));
        countries.add(new Country(genID(), "090", "677", "SBD", "SOLOMON ISLANDS"));
        countries.add(new Country(genID(), "706", "252", "SOS", "SOMALIA"));
        countries.add(new Country(genID(), "710", "27", "ZAR", "SOUTH AFRICA"));
        countries.add(new Country(genID(), "728", "211", "SSP", "SOUTH SUDAN"));
        countries.add(new Country(genID(), "978", "34", "EUR", "SPAIN"));
        countries.add(new Country(genID(), "144", "94", "LKR", "SRI LANKA"));
        countries.add(new Country(genID(), "938", "249", "SDG", "SUDAN (THE)"));
        countries.add(new Country(genID(), "968", "597", "SRD", "SURINAME"));
        countries.add(new Country(genID(), "578", "47", "NOK", "SVALBARD AND JAN MAYEN"));
        countries.add(new Country(genID(), "748", "268", "SZL", "SWAZILAND"));
        countries.add(new Country(genID(), "752", "46", "SEK", "SWEDEN"));
        countries.add(new Country(genID(), "756", "41", "CHF", "SWITZERLAND"));
        countries.add(new Country(genID(), "760", "963", "SYP", "SYRIAN ARAB REPUBLIC"));
        countries.add(new Country(genID(), "901", "886", "TWD", "TAIWAN"));
        countries.add(new Country(genID(), "972", "992", "TJS", "TAJIKISTAN"));
        countries.add(new Country(genID(), "834", "255", "TZS", "TANZANIA"));
        countries.add(new Country(genID(), "764", "66", "THB", "THAILAND"));
        countries.add(new Country(genID(), "840", "670", "USD", "TIMOR-LESTE"));
        countries.add(new Country(genID(), "952", "228", "XOF", "TOGO"));
        countries.add(new Country(genID(), "554", "690", "NZD", "TOKELAU"));
        countries.add(new Country(genID(), "776", "676", "TOP", "TONGA"));
        countries.add(new Country(genID(), "780", "1", "TTD", "TRINIDAD AND TOBAGO"));
        countries.add(new Country(genID(), "788", "216", "TND", "TUNISIA"));
        countries.add(new Country(genID(), "949", "90", "TRY", "TURKEY"));
        countries.add(new Country(genID(), "934", "993", "TMT", "TURKMENISTAN"));
        countries.add(new Country(genID(), "840", "1", "USD", "TURKS AND CAICOS ISLANDS (THE)"));
        countries.add(new Country(genID(), "036", "688", "AUD", "TUVALU"));
        countries.add(new Country(genID(), "800", "256", "UGX", "UGANDA"));
        countries.add(new Country(genID(), "980", "380", "UAH", "UKRAINE"));
        countries.add(new Country(genID(), "784", "971", "AED", "UNITED ARAB EMIRATES (THE)"));
        countries.add(new Country(genID(), "826", "44", "GBP", "UNITED KINGDOM"));
        countries.add(new Country(genID(), "826", "353", "GBP", "NORTHERN IRELAND"));
        countries.add(new Country(genID(), "840", "1", "USD", "UNITED STATES MINOR OUTLYING ISLANDS (THE)"));
        countries.add(new Country(genID(), "840", "1", "USD", "UNITED STATES OF AMERICA (THE)"));
        countries.add(new Country(genID(), "858", "598", "UYU", "URUGUAY"));
        countries.add(new Country(genID(), "860", "998", "UZS", "UZBEKISTAN"));
        countries.add(new Country(genID(), "548", "678", "VUV", "VANUATU"));
        countries.add(new Country(genID(), "937", "58", "VEF", "VENEZUELA (BOLIVARIAN REPUBLIC OF)"));
        countries.add(new Country(genID(), "704", "84", "VND", "VIET NAM"));
        countries.add(new Country(genID(), "840", "1284", "USD", "VIRGIN ISLANDS (BRITISH)"));
        countries.add(new Country(genID(), "840", "1", "USD", "VIRGIN ISLANDS (U.S.)"));
        countries.add(new Country(genID(), "953", "681", "XPF", "WALLIS AND FUTUNA"));
        countries.add(new Country(genID(), "504", "212", "MAD", "WESTERN SAHARA"));
        countries.add(new Country(genID(), "886", "967", "YER", "YEMEN"));
        countries.add(new Country(genID(), "967", "260", "ZMW", "ZAMBIA"));
        countries.add(new Country(genID(), "932", "263", "ZWL", "ZIMBABWE"));
        countries.add(new Country(genID(), "978", "358", "EUR", "ALAND ISLANDS"));
        return countries;
    }

    public static List<String> getCountryNamesList(List<Country> countries){
        List<String> result = new ArrayList<>();
        for (Country c: countries) {
            result.add(c.countryName);
        }
        return result;
    }

    public static String[] getCurrencyCodes(){
        List<Country> countries = getMemCountries();
        Map<String, String> result = new HashMap<>();
        for (Country c: countries) {
            if(!result.containsKey(c.currencyCode)){
                result.put(c.currencyCode, c.currencyCode);
            }
        }
        return (String[]) result.values().toArray();
    }

    public void getCountries(){
        ValueEventListener onDataChangedListener =  new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        List<Country> result = new ArrayList<>();
                        //
                        if (snapshot.hasChildren()){
                            for(DataSnapshot userSnapshot:snapshot.getChildren()){
                                Country country = userSnapshot.getValue(Country.class);
                                result.add(country);
                            }
                            //raise event
                            countryEvents.onCountriesFetched(result);
                        }
                    }
                }else{
                    //Add countries and raise the countries fetched event.
                    AddCountry();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Add countries and raise the countries fetched event.
                AddCountry();
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.COUNTRIES())
                .orderByChild("countries");
        query.addListenerForSingleValueEvent(onDataChangedListener);
        //
    }


}

