package edu.fontbonne.mobileapplab.flit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.cert.PKIXCertPathBuilderResult;

/**
 * Created by rick on 4/11/16.
 */
public class FLITDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FLIT.db";

    public static final String USER_TABLE_NAME = "USER";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_REASON = "reason";
    public static final String USER_COLUMN_STUDENT = "is_student";
    public static final String USER_COLUMN_DATE = "registration_date";

    public static final String SPENDING_TABLE_NAME = "SPENDING";
    public static final String SPENDING_COLUMN_EMAIL = "email";
    public static final String SPENDING_COLUMN_REASON = "reason";
    public static final String SPENDING_COLUMN_LOCATION = "location";
    public static final String SPENDING_COLUMN_AMOUNT = "amount";
    public static final String SPENDING_COLUMN_TIME = "time";

    public static final String CLOTHING_TABLE_NAME = "CLOTHING_BUDGET";
    public static final String CLOTHING_COLUMN_EMAIL = "email";
    public static final String CLOTHING_COLUMN_CLOTHING = "clothing";

    public static final String EMERGENCY_TABLE_NAME = "EMERGENCY_BUDGET";
    public static final String EMERGENCY_COLUMN_EMAIL = "email";
    public static final String EMERGENCY_COLUMN_EMERGENCY = "emergency";

    public static final String ENTERTAINMENT_TABLE_NAME = "ENTERTAINMENT_BUDGET";
    public static final String ENTERTAINMENT_COLUMN_EMAIL = "email";
    public static final String ENTERTAINMENT_COLUMN_GAMES = "video_games";
    public static final String ENTERTAINMENT_COLUMN_MOVIES = "movies";
    public static final String ENTERTAINMENT_COLUMN_COMPUTER = "computer";
    public static final String ENTERTAINMENT_COLUMN_HOBBIES = "hobbies";
    public static final String ENTERTAINMENT_COLUMN_VACATIONS = "vacations";
    public static final String ENTERTAINMENT_COLUMN_OTHER = "other";

    public static final String FOOD_TABLE_NAME = "FOOD_BUDGET";
    public static final String FOOD_COLUMN_EMAIL = "email";
    public static final String FOOD_COLUMN_GROCERIES = "groceries";
    public static final String FOOD_COLUMN_EATING = "eating_out";
    public static final String FOOD_COLUMN_OTHER = "other";

    public static final String HEALTH_TABLE_NAME = "HEALTH_BUDGET";
    public static final String HEALTH_COLUMN_EMAIL = "email";
    public static final String HEALTH_COLUMN_INSURANCE = "insurance";
    public static final String HEALTH_COLUMN_BILLS = "bills";
    public static final String HEALTH_COLUMN_FITNESS = "fitness";
    public static final String HEALTH_COLUMN_OTHER = "other";

    public static final String HOME_TABLE_NAME = "HOME_BUDGET";
    public static final String HOME_COLUMN_EMAIL = "email";
    public static final String HOME_COLUMN_RENT = "rent_mortgage";
    public static final String HOME_COLUMN_ELECTRICITY = "electricity";
    public static final String HOME_COLUMN_WATER = "water_sewer";
    public static final String HOME_COLUMN_GAS = "gas";
    public static final String HOME_COLUMN_PHONE = "phone";
    public static final String HOME_COLUMN_CABLE = "cable";
    public static final String HOME_COLUMN_INTERNET = "internet";
    public static final String HOME_COLUMN_OTHER = "other";

    public static final String PAYMENTS_TABLE_NAME = "PAYMENTS_BUDGET";
    public static final String PAYMENTS_COLUMN_EMAIL = "email";
    public static final String PAYMENTS_COLUMN_CREDIT = "credit_cards";
    public static final String PAYMENTS_COLUMN_LOANS = "student_loans";
    public static final String PAYMENTS_COLUMN_OTHER = "other";

    public static final String SAVINGS_TABLE_NAME = "SAVINGS_BUDGET";
    public static final String SAVINGS_COLUMN_EMAIL = "email";
    public static final String SAVINGS_COLUMN_SAVINGS = "savings";

    public static final String TRANSPORT_TABLE_NAME = "TRANSPORT_BUDGET";
    public static final String TRANSPORT_COLUMN_EMAIL = "email";
    public static final String TRANSPORT_COLUMN_CAR = "car_payments";
    public static final String TRANSPORT_COLUMN_GAS = "gas_oil";
    public static final String TRANSPORT_COLUMN_REPAIRS = "repairs";
    public static final String TRANSPORT_COLUMN_INSURANCE = "insurance";
    public static final String TRANSPORT_COLUMN_OTHER = "other";

    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    USER_COLUMN_EMAIL + " VARCHAR(40)," +
                    USER_COLUMN_PASSWORD + " VARCHAR(20)," +
                    USER_COLUMN_NAME + " VARCHAR(30)," +
                    USER_COLUMN_REASON + " VARCHAR(100)," +
                    USER_COLUMN_STUDENT + " TINYINT(1)," +
                    USER_COLUMN_DATE + " DATE)";

    private static final String CREATE_TABLE_SPENDING =
            "CREATE TABLE " + SPENDING_TABLE_NAME + " (" +
                    SPENDING_COLUMN_EMAIL + " VARCHAR(40)," +
                    SPENDING_COLUMN_REASON + " VARCHAR(20)," +
                    SPENDING_COLUMN_LOCATION + " VARCHAR(50)," +
                    SPENDING_COLUMN_AMOUNT + " FLOAT," +
                    SPENDING_COLUMN_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_TABLE_CLOTHING =
            "CREATE TABLE " + CLOTHING_TABLE_NAME + " (" +
                    CLOTHING_COLUMN_EMAIL + " VARCHAR(40)," +
                    CLOTHING_COLUMN_CLOTHING + " FLOAT)";

    private static final String CREATE_TABLE_EMERGENCY =
            "CREATE TABLE " + EMERGENCY_TABLE_NAME + " (" +
                    EMERGENCY_COLUMN_EMAIL + " VARCHAR(40)," +
                    EMERGENCY_COLUMN_EMERGENCY + " FLOAT)";

    private static final String CREATE_TABLE_ENTERTAINMENT =
            "CREATE TABLE " + ENTERTAINMENT_TABLE_NAME + " (" +
                    ENTERTAINMENT_COLUMN_EMAIL + " VARCHAR(40)," +
                    ENTERTAINMENT_COLUMN_GAMES + " FLOAT," +
                    ENTERTAINMENT_COLUMN_MOVIES + " FLOAT," +
                    ENTERTAINMENT_COLUMN_COMPUTER + " FLOAT," +
                    ENTERTAINMENT_COLUMN_HOBBIES + " FLOAT," +
                    ENTERTAINMENT_COLUMN_VACATIONS + " FLOAT," +
                    ENTERTAINMENT_COLUMN_OTHER + " FLOAT)";

    private static final String CREATE_TABLE_FOOD =
            "CREATE TABLE " + FOOD_TABLE_NAME + " (" +
                    FOOD_COLUMN_EMAIL + " VARCHAR(40)," +
                    FOOD_COLUMN_GROCERIES + " FLOAT," +
                    FOOD_COLUMN_EATING + " FLOAT," +
                    FOOD_COLUMN_OTHER + " FLOAT)";

    private static final String CREATE_TABLE_HEALTH =
            "CREATE TABLE " + HEALTH_TABLE_NAME + " (" +
                    HEALTH_COLUMN_EMAIL + " VARCHAR(40)," +
                    HEALTH_COLUMN_INSURANCE + " FLOAT," +
                    HEALTH_COLUMN_BILLS + " FLOAT," +
                    HEALTH_COLUMN_FITNESS + " FLOAT," +
                    HEALTH_COLUMN_OTHER + " FLOAT)";

    private static final String CREATE_TABLE_HOME =
            "CREATE TABLE " + HOME_TABLE_NAME + " (" +
                    HOME_COLUMN_EMAIL + " VARCHAR(40)," +
                    HOME_COLUMN_RENT + " FLOAT," +
                    HOME_COLUMN_ELECTRICITY + " FLOAT," +
                    HOME_COLUMN_WATER + " FLOAT," +
                    HOME_COLUMN_GAS + " FLOAT," +
                    HOME_COLUMN_PHONE + " FLOAT," +
                    HOME_COLUMN_CABLE + " FLOAT," +
                    HOME_COLUMN_INTERNET + " FLOAT," +
                    HOME_COLUMN_OTHER + " FLOAT)";

    private static final String CREATE_TABLE_PAYMENTS =
            "CREATE TABLE " + PAYMENTS_TABLE_NAME + " (" +
                    PAYMENTS_COLUMN_EMAIL + " VARCHAR(40)," +
                    PAYMENTS_COLUMN_CREDIT + " FLOAT," +
                    PAYMENTS_COLUMN_LOANS + " FLOAT," +
                    PAYMENTS_COLUMN_OTHER + " FLOAT)";

    private static final String CREATE_TABLE_SAVINGS =
            "CREATE TABLE " + SAVINGS_TABLE_NAME + " (" +
                    SAVINGS_COLUMN_EMAIL + " VARCHAR(40)," +
                    SAVINGS_COLUMN_SAVINGS + " FLOAT)";

    private static final String CREATE_TABLE_TRANSPORT =
            "CREATE TABLE " + TRANSPORT_TABLE_NAME + " (" +
                    TRANSPORT_COLUMN_EMAIL + " VARCHAR(40)," +
                    TRANSPORT_COLUMN_CAR + " FLOAT," +
                    TRANSPORT_COLUMN_GAS + " FLOAT," +
                    TRANSPORT_COLUMN_REPAIRS + " FLOAT," +
                    TRANSPORT_COLUMN_INSURANCE + " FLOAT," +
                    TRANSPORT_COLUMN_OTHER + " FLOAT)";

    public FLITDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_SPENDING);
        db.execSQL(CREATE_TABLE_CLOTHING);
        db.execSQL(CREATE_TABLE_EMERGENCY);
        db.execSQL(CREATE_TABLE_ENTERTAINMENT);
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_HEALTH);
        db.execSQL(CREATE_TABLE_HOME);
        db.execSQL(CREATE_TABLE_PAYMENTS);
        db.execSQL(CREATE_TABLE_SAVINGS);
        db.execSQL(CREATE_TABLE_TRANSPORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SPENDING_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CLOTHING_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EMERGENCY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ENTERTAINMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HEALTH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HOME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PAYMENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SAVINGS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSPORT_TABLE_NAME);
        onCreate(db);
    }
}
