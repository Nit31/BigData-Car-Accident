// ORM class for table 'accidents'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sat Apr 19 19:07:52 MSK 2025
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class codegen_accidents extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.id = (String)value;
      }
    });
    setters.put("severity", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.severity = (Integer)value;
      }
    });
    setters.put("start_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.start_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("end_time", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.end_time = (java.sql.Timestamp)value;
      }
    });
    setters.put("start_lat", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.start_lat = (java.math.BigDecimal)value;
      }
    });
    setters.put("start_lng", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.start_lng = (java.math.BigDecimal)value;
      }
    });
    setters.put("end_lat", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.end_lat = (java.math.BigDecimal)value;
      }
    });
    setters.put("end_lng", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.end_lng = (java.math.BigDecimal)value;
      }
    });
    setters.put("distance_mi", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.distance_mi = (java.math.BigDecimal)value;
      }
    });
    setters.put("side", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.side = (String)value;
      }
    });
    setters.put("city", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.city = (String)value;
      }
    });
    setters.put("county", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.county = (String)value;
      }
    });
    setters.put("state", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.state = (String)value;
      }
    });
    setters.put("weather_timestamp", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.weather_timestamp = (java.sql.Timestamp)value;
      }
    });
    setters.put("temperature_f", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.temperature_f = (java.math.BigDecimal)value;
      }
    });
    setters.put("wind_chill_f", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.wind_chill_f = (java.math.BigDecimal)value;
      }
    });
    setters.put("humidity_percent", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.humidity_percent = (java.math.BigDecimal)value;
      }
    });
    setters.put("pressure_in", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.pressure_in = (java.math.BigDecimal)value;
      }
    });
    setters.put("visibility_mi", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.visibility_mi = (java.math.BigDecimal)value;
      }
    });
    setters.put("wind_speed_mph", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.wind_speed_mph = (java.math.BigDecimal)value;
      }
    });
    setters.put("precipitation_in", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.precipitation_in = (java.math.BigDecimal)value;
      }
    });
    setters.put("weather_condition", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.weather_condition = (String)value;
      }
    });
    setters.put("sunrise_sunset", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        codegen_accidents.this.sunrise_sunset = (String)value;
      }
    });
  }
  public codegen_accidents() {
    init0();
  }
  private String id;
  public String get_id() {
    return id;
  }
  public void set_id(String id) {
    this.id = id;
  }
  public codegen_accidents with_id(String id) {
    this.id = id;
    return this;
  }
  private Integer severity;
  public Integer get_severity() {
    return severity;
  }
  public void set_severity(Integer severity) {
    this.severity = severity;
  }
  public codegen_accidents with_severity(Integer severity) {
    this.severity = severity;
    return this;
  }
  private java.sql.Timestamp start_time;
  public java.sql.Timestamp get_start_time() {
    return start_time;
  }
  public void set_start_time(java.sql.Timestamp start_time) {
    this.start_time = start_time;
  }
  public codegen_accidents with_start_time(java.sql.Timestamp start_time) {
    this.start_time = start_time;
    return this;
  }
  private java.sql.Timestamp end_time;
  public java.sql.Timestamp get_end_time() {
    return end_time;
  }
  public void set_end_time(java.sql.Timestamp end_time) {
    this.end_time = end_time;
  }
  public codegen_accidents with_end_time(java.sql.Timestamp end_time) {
    this.end_time = end_time;
    return this;
  }
  private java.math.BigDecimal start_lat;
  public java.math.BigDecimal get_start_lat() {
    return start_lat;
  }
  public void set_start_lat(java.math.BigDecimal start_lat) {
    this.start_lat = start_lat;
  }
  public codegen_accidents with_start_lat(java.math.BigDecimal start_lat) {
    this.start_lat = start_lat;
    return this;
  }
  private java.math.BigDecimal start_lng;
  public java.math.BigDecimal get_start_lng() {
    return start_lng;
  }
  public void set_start_lng(java.math.BigDecimal start_lng) {
    this.start_lng = start_lng;
  }
  public codegen_accidents with_start_lng(java.math.BigDecimal start_lng) {
    this.start_lng = start_lng;
    return this;
  }
  private java.math.BigDecimal end_lat;
  public java.math.BigDecimal get_end_lat() {
    return end_lat;
  }
  public void set_end_lat(java.math.BigDecimal end_lat) {
    this.end_lat = end_lat;
  }
  public codegen_accidents with_end_lat(java.math.BigDecimal end_lat) {
    this.end_lat = end_lat;
    return this;
  }
  private java.math.BigDecimal end_lng;
  public java.math.BigDecimal get_end_lng() {
    return end_lng;
  }
  public void set_end_lng(java.math.BigDecimal end_lng) {
    this.end_lng = end_lng;
  }
  public codegen_accidents with_end_lng(java.math.BigDecimal end_lng) {
    this.end_lng = end_lng;
    return this;
  }
  private java.math.BigDecimal distance_mi;
  public java.math.BigDecimal get_distance_mi() {
    return distance_mi;
  }
  public void set_distance_mi(java.math.BigDecimal distance_mi) {
    this.distance_mi = distance_mi;
  }
  public codegen_accidents with_distance_mi(java.math.BigDecimal distance_mi) {
    this.distance_mi = distance_mi;
    return this;
  }
  private String side;
  public String get_side() {
    return side;
  }
  public void set_side(String side) {
    this.side = side;
  }
  public codegen_accidents with_side(String side) {
    this.side = side;
    return this;
  }
  private String city;
  public String get_city() {
    return city;
  }
  public void set_city(String city) {
    this.city = city;
  }
  public codegen_accidents with_city(String city) {
    this.city = city;
    return this;
  }
  private String county;
  public String get_county() {
    return county;
  }
  public void set_county(String county) {
    this.county = county;
  }
  public codegen_accidents with_county(String county) {
    this.county = county;
    return this;
  }
  private String state;
  public String get_state() {
    return state;
  }
  public void set_state(String state) {
    this.state = state;
  }
  public codegen_accidents with_state(String state) {
    this.state = state;
    return this;
  }
  private java.sql.Timestamp weather_timestamp;
  public java.sql.Timestamp get_weather_timestamp() {
    return weather_timestamp;
  }
  public void set_weather_timestamp(java.sql.Timestamp weather_timestamp) {
    this.weather_timestamp = weather_timestamp;
  }
  public codegen_accidents with_weather_timestamp(java.sql.Timestamp weather_timestamp) {
    this.weather_timestamp = weather_timestamp;
    return this;
  }
  private java.math.BigDecimal temperature_f;
  public java.math.BigDecimal get_temperature_f() {
    return temperature_f;
  }
  public void set_temperature_f(java.math.BigDecimal temperature_f) {
    this.temperature_f = temperature_f;
  }
  public codegen_accidents with_temperature_f(java.math.BigDecimal temperature_f) {
    this.temperature_f = temperature_f;
    return this;
  }
  private java.math.BigDecimal wind_chill_f;
  public java.math.BigDecimal get_wind_chill_f() {
    return wind_chill_f;
  }
  public void set_wind_chill_f(java.math.BigDecimal wind_chill_f) {
    this.wind_chill_f = wind_chill_f;
  }
  public codegen_accidents with_wind_chill_f(java.math.BigDecimal wind_chill_f) {
    this.wind_chill_f = wind_chill_f;
    return this;
  }
  private java.math.BigDecimal humidity_percent;
  public java.math.BigDecimal get_humidity_percent() {
    return humidity_percent;
  }
  public void set_humidity_percent(java.math.BigDecimal humidity_percent) {
    this.humidity_percent = humidity_percent;
  }
  public codegen_accidents with_humidity_percent(java.math.BigDecimal humidity_percent) {
    this.humidity_percent = humidity_percent;
    return this;
  }
  private java.math.BigDecimal pressure_in;
  public java.math.BigDecimal get_pressure_in() {
    return pressure_in;
  }
  public void set_pressure_in(java.math.BigDecimal pressure_in) {
    this.pressure_in = pressure_in;
  }
  public codegen_accidents with_pressure_in(java.math.BigDecimal pressure_in) {
    this.pressure_in = pressure_in;
    return this;
  }
  private java.math.BigDecimal visibility_mi;
  public java.math.BigDecimal get_visibility_mi() {
    return visibility_mi;
  }
  public void set_visibility_mi(java.math.BigDecimal visibility_mi) {
    this.visibility_mi = visibility_mi;
  }
  public codegen_accidents with_visibility_mi(java.math.BigDecimal visibility_mi) {
    this.visibility_mi = visibility_mi;
    return this;
  }
  private java.math.BigDecimal wind_speed_mph;
  public java.math.BigDecimal get_wind_speed_mph() {
    return wind_speed_mph;
  }
  public void set_wind_speed_mph(java.math.BigDecimal wind_speed_mph) {
    this.wind_speed_mph = wind_speed_mph;
  }
  public codegen_accidents with_wind_speed_mph(java.math.BigDecimal wind_speed_mph) {
    this.wind_speed_mph = wind_speed_mph;
    return this;
  }
  private java.math.BigDecimal precipitation_in;
  public java.math.BigDecimal get_precipitation_in() {
    return precipitation_in;
  }
  public void set_precipitation_in(java.math.BigDecimal precipitation_in) {
    this.precipitation_in = precipitation_in;
  }
  public codegen_accidents with_precipitation_in(java.math.BigDecimal precipitation_in) {
    this.precipitation_in = precipitation_in;
    return this;
  }
  private String weather_condition;
  public String get_weather_condition() {
    return weather_condition;
  }
  public void set_weather_condition(String weather_condition) {
    this.weather_condition = weather_condition;
  }
  public codegen_accidents with_weather_condition(String weather_condition) {
    this.weather_condition = weather_condition;
    return this;
  }
  private String sunrise_sunset;
  public String get_sunrise_sunset() {
    return sunrise_sunset;
  }
  public void set_sunrise_sunset(String sunrise_sunset) {
    this.sunrise_sunset = sunrise_sunset;
  }
  public codegen_accidents with_sunrise_sunset(String sunrise_sunset) {
    this.sunrise_sunset = sunrise_sunset;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof codegen_accidents)) {
      return false;
    }
    codegen_accidents that = (codegen_accidents) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.severity == null ? that.severity == null : this.severity.equals(that.severity));
    equal = equal && (this.start_time == null ? that.start_time == null : this.start_time.equals(that.start_time));
    equal = equal && (this.end_time == null ? that.end_time == null : this.end_time.equals(that.end_time));
    equal = equal && (this.start_lat == null ? that.start_lat == null : this.start_lat.equals(that.start_lat));
    equal = equal && (this.start_lng == null ? that.start_lng == null : this.start_lng.equals(that.start_lng));
    equal = equal && (this.end_lat == null ? that.end_lat == null : this.end_lat.equals(that.end_lat));
    equal = equal && (this.end_lng == null ? that.end_lng == null : this.end_lng.equals(that.end_lng));
    equal = equal && (this.distance_mi == null ? that.distance_mi == null : this.distance_mi.equals(that.distance_mi));
    equal = equal && (this.side == null ? that.side == null : this.side.equals(that.side));
    equal = equal && (this.city == null ? that.city == null : this.city.equals(that.city));
    equal = equal && (this.county == null ? that.county == null : this.county.equals(that.county));
    equal = equal && (this.state == null ? that.state == null : this.state.equals(that.state));
    equal = equal && (this.weather_timestamp == null ? that.weather_timestamp == null : this.weather_timestamp.equals(that.weather_timestamp));
    equal = equal && (this.temperature_f == null ? that.temperature_f == null : this.temperature_f.equals(that.temperature_f));
    equal = equal && (this.wind_chill_f == null ? that.wind_chill_f == null : this.wind_chill_f.equals(that.wind_chill_f));
    equal = equal && (this.humidity_percent == null ? that.humidity_percent == null : this.humidity_percent.equals(that.humidity_percent));
    equal = equal && (this.pressure_in == null ? that.pressure_in == null : this.pressure_in.equals(that.pressure_in));
    equal = equal && (this.visibility_mi == null ? that.visibility_mi == null : this.visibility_mi.equals(that.visibility_mi));
    equal = equal && (this.wind_speed_mph == null ? that.wind_speed_mph == null : this.wind_speed_mph.equals(that.wind_speed_mph));
    equal = equal && (this.precipitation_in == null ? that.precipitation_in == null : this.precipitation_in.equals(that.precipitation_in));
    equal = equal && (this.weather_condition == null ? that.weather_condition == null : this.weather_condition.equals(that.weather_condition));
    equal = equal && (this.sunrise_sunset == null ? that.sunrise_sunset == null : this.sunrise_sunset.equals(that.sunrise_sunset));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof codegen_accidents)) {
      return false;
    }
    codegen_accidents that = (codegen_accidents) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.severity == null ? that.severity == null : this.severity.equals(that.severity));
    equal = equal && (this.start_time == null ? that.start_time == null : this.start_time.equals(that.start_time));
    equal = equal && (this.end_time == null ? that.end_time == null : this.end_time.equals(that.end_time));
    equal = equal && (this.start_lat == null ? that.start_lat == null : this.start_lat.equals(that.start_lat));
    equal = equal && (this.start_lng == null ? that.start_lng == null : this.start_lng.equals(that.start_lng));
    equal = equal && (this.end_lat == null ? that.end_lat == null : this.end_lat.equals(that.end_lat));
    equal = equal && (this.end_lng == null ? that.end_lng == null : this.end_lng.equals(that.end_lng));
    equal = equal && (this.distance_mi == null ? that.distance_mi == null : this.distance_mi.equals(that.distance_mi));
    equal = equal && (this.side == null ? that.side == null : this.side.equals(that.side));
    equal = equal && (this.city == null ? that.city == null : this.city.equals(that.city));
    equal = equal && (this.county == null ? that.county == null : this.county.equals(that.county));
    equal = equal && (this.state == null ? that.state == null : this.state.equals(that.state));
    equal = equal && (this.weather_timestamp == null ? that.weather_timestamp == null : this.weather_timestamp.equals(that.weather_timestamp));
    equal = equal && (this.temperature_f == null ? that.temperature_f == null : this.temperature_f.equals(that.temperature_f));
    equal = equal && (this.wind_chill_f == null ? that.wind_chill_f == null : this.wind_chill_f.equals(that.wind_chill_f));
    equal = equal && (this.humidity_percent == null ? that.humidity_percent == null : this.humidity_percent.equals(that.humidity_percent));
    equal = equal && (this.pressure_in == null ? that.pressure_in == null : this.pressure_in.equals(that.pressure_in));
    equal = equal && (this.visibility_mi == null ? that.visibility_mi == null : this.visibility_mi.equals(that.visibility_mi));
    equal = equal && (this.wind_speed_mph == null ? that.wind_speed_mph == null : this.wind_speed_mph.equals(that.wind_speed_mph));
    equal = equal && (this.precipitation_in == null ? that.precipitation_in == null : this.precipitation_in.equals(that.precipitation_in));
    equal = equal && (this.weather_condition == null ? that.weather_condition == null : this.weather_condition.equals(that.weather_condition));
    equal = equal && (this.sunrise_sunset == null ? that.sunrise_sunset == null : this.sunrise_sunset.equals(that.sunrise_sunset));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readString(1, __dbResults);
    this.severity = JdbcWritableBridge.readInteger(2, __dbResults);
    this.start_time = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.end_time = JdbcWritableBridge.readTimestamp(4, __dbResults);
    this.start_lat = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.start_lng = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.end_lat = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.end_lng = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.distance_mi = JdbcWritableBridge.readBigDecimal(9, __dbResults);
    this.side = JdbcWritableBridge.readString(10, __dbResults);
    this.city = JdbcWritableBridge.readString(11, __dbResults);
    this.county = JdbcWritableBridge.readString(12, __dbResults);
    this.state = JdbcWritableBridge.readString(13, __dbResults);
    this.weather_timestamp = JdbcWritableBridge.readTimestamp(14, __dbResults);
    this.temperature_f = JdbcWritableBridge.readBigDecimal(15, __dbResults);
    this.wind_chill_f = JdbcWritableBridge.readBigDecimal(16, __dbResults);
    this.humidity_percent = JdbcWritableBridge.readBigDecimal(17, __dbResults);
    this.pressure_in = JdbcWritableBridge.readBigDecimal(18, __dbResults);
    this.visibility_mi = JdbcWritableBridge.readBigDecimal(19, __dbResults);
    this.wind_speed_mph = JdbcWritableBridge.readBigDecimal(20, __dbResults);
    this.precipitation_in = JdbcWritableBridge.readBigDecimal(21, __dbResults);
    this.weather_condition = JdbcWritableBridge.readString(22, __dbResults);
    this.sunrise_sunset = JdbcWritableBridge.readString(23, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id = JdbcWritableBridge.readString(1, __dbResults);
    this.severity = JdbcWritableBridge.readInteger(2, __dbResults);
    this.start_time = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.end_time = JdbcWritableBridge.readTimestamp(4, __dbResults);
    this.start_lat = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.start_lng = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.end_lat = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.end_lng = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.distance_mi = JdbcWritableBridge.readBigDecimal(9, __dbResults);
    this.side = JdbcWritableBridge.readString(10, __dbResults);
    this.city = JdbcWritableBridge.readString(11, __dbResults);
    this.county = JdbcWritableBridge.readString(12, __dbResults);
    this.state = JdbcWritableBridge.readString(13, __dbResults);
    this.weather_timestamp = JdbcWritableBridge.readTimestamp(14, __dbResults);
    this.temperature_f = JdbcWritableBridge.readBigDecimal(15, __dbResults);
    this.wind_chill_f = JdbcWritableBridge.readBigDecimal(16, __dbResults);
    this.humidity_percent = JdbcWritableBridge.readBigDecimal(17, __dbResults);
    this.pressure_in = JdbcWritableBridge.readBigDecimal(18, __dbResults);
    this.visibility_mi = JdbcWritableBridge.readBigDecimal(19, __dbResults);
    this.wind_speed_mph = JdbcWritableBridge.readBigDecimal(20, __dbResults);
    this.precipitation_in = JdbcWritableBridge.readBigDecimal(21, __dbResults);
    this.weather_condition = JdbcWritableBridge.readString(22, __dbResults);
    this.sunrise_sunset = JdbcWritableBridge.readString(23, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(id, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(severity, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(start_time, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(end_time, 4 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(start_lat, 5 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(start_lng, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(end_lat, 7 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(end_lng, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(distance_mi, 9 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(side, 10 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(city, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(county, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(state, 13 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeTimestamp(weather_timestamp, 14 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(temperature_f, 15 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(wind_chill_f, 16 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(humidity_percent, 17 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(pressure_in, 18 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(visibility_mi, 19 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(wind_speed_mph, 20 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(precipitation_in, 21 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(weather_condition, 22 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sunrise_sunset, 23 + __off, 12, __dbStmt);
    return 23;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(id, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(severity, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(start_time, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeTimestamp(end_time, 4 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(start_lat, 5 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(start_lng, 6 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(end_lat, 7 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(end_lng, 8 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(distance_mi, 9 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(side, 10 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(city, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(county, 12 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(state, 13 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeTimestamp(weather_timestamp, 14 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(temperature_f, 15 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(wind_chill_f, 16 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(humidity_percent, 17 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(pressure_in, 18 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(visibility_mi, 19 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(wind_speed_mph, 20 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(precipitation_in, 21 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(weather_condition, 22 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sunrise_sunset, 23 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.severity = null;
    } else {
    this.severity = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.start_time = null;
    } else {
    this.start_time = new Timestamp(__dataIn.readLong());
    this.start_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.end_time = null;
    } else {
    this.end_time = new Timestamp(__dataIn.readLong());
    this.end_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.start_lat = null;
    } else {
    this.start_lat = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.start_lng = null;
    } else {
    this.start_lng = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.end_lat = null;
    } else {
    this.end_lat = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.end_lng = null;
    } else {
    this.end_lng = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.distance_mi = null;
    } else {
    this.distance_mi = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.side = null;
    } else {
    this.side = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.city = null;
    } else {
    this.city = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.county = null;
    } else {
    this.county = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.state = null;
    } else {
    this.state = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.weather_timestamp = null;
    } else {
    this.weather_timestamp = new Timestamp(__dataIn.readLong());
    this.weather_timestamp.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.temperature_f = null;
    } else {
    this.temperature_f = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.wind_chill_f = null;
    } else {
    this.wind_chill_f = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.humidity_percent = null;
    } else {
    this.humidity_percent = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.pressure_in = null;
    } else {
    this.pressure_in = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.visibility_mi = null;
    } else {
    this.visibility_mi = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.wind_speed_mph = null;
    } else {
    this.wind_speed_mph = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.precipitation_in = null;
    } else {
    this.precipitation_in = org.apache.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.weather_condition = null;
    } else {
    this.weather_condition = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.sunrise_sunset = null;
    } else {
    this.sunrise_sunset = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, id);
    }
    if (null == this.severity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.severity);
    }
    if (null == this.start_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.start_time.getTime());
    __dataOut.writeInt(this.start_time.getNanos());
    }
    if (null == this.end_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.end_time.getTime());
    __dataOut.writeInt(this.end_time.getNanos());
    }
    if (null == this.start_lat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.start_lat, __dataOut);
    }
    if (null == this.start_lng) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.start_lng, __dataOut);
    }
    if (null == this.end_lat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.end_lat, __dataOut);
    }
    if (null == this.end_lng) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.end_lng, __dataOut);
    }
    if (null == this.distance_mi) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.distance_mi, __dataOut);
    }
    if (null == this.side) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, side);
    }
    if (null == this.city) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, city);
    }
    if (null == this.county) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, county);
    }
    if (null == this.state) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, state);
    }
    if (null == this.weather_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.weather_timestamp.getTime());
    __dataOut.writeInt(this.weather_timestamp.getNanos());
    }
    if (null == this.temperature_f) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.temperature_f, __dataOut);
    }
    if (null == this.wind_chill_f) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.wind_chill_f, __dataOut);
    }
    if (null == this.humidity_percent) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.humidity_percent, __dataOut);
    }
    if (null == this.pressure_in) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.pressure_in, __dataOut);
    }
    if (null == this.visibility_mi) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.visibility_mi, __dataOut);
    }
    if (null == this.wind_speed_mph) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.wind_speed_mph, __dataOut);
    }
    if (null == this.precipitation_in) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.precipitation_in, __dataOut);
    }
    if (null == this.weather_condition) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, weather_condition);
    }
    if (null == this.sunrise_sunset) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sunrise_sunset);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, id);
    }
    if (null == this.severity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.severity);
    }
    if (null == this.start_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.start_time.getTime());
    __dataOut.writeInt(this.start_time.getNanos());
    }
    if (null == this.end_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.end_time.getTime());
    __dataOut.writeInt(this.end_time.getNanos());
    }
    if (null == this.start_lat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.start_lat, __dataOut);
    }
    if (null == this.start_lng) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.start_lng, __dataOut);
    }
    if (null == this.end_lat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.end_lat, __dataOut);
    }
    if (null == this.end_lng) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.end_lng, __dataOut);
    }
    if (null == this.distance_mi) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.distance_mi, __dataOut);
    }
    if (null == this.side) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, side);
    }
    if (null == this.city) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, city);
    }
    if (null == this.county) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, county);
    }
    if (null == this.state) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, state);
    }
    if (null == this.weather_timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.weather_timestamp.getTime());
    __dataOut.writeInt(this.weather_timestamp.getNanos());
    }
    if (null == this.temperature_f) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.temperature_f, __dataOut);
    }
    if (null == this.wind_chill_f) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.wind_chill_f, __dataOut);
    }
    if (null == this.humidity_percent) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.humidity_percent, __dataOut);
    }
    if (null == this.pressure_in) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.pressure_in, __dataOut);
    }
    if (null == this.visibility_mi) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.visibility_mi, __dataOut);
    }
    if (null == this.wind_speed_mph) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.wind_speed_mph, __dataOut);
    }
    if (null == this.precipitation_in) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    org.apache.sqoop.lib.BigDecimalSerializer.write(this.precipitation_in, __dataOut);
    }
    if (null == this.weather_condition) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, weather_condition);
    }
    if (null == this.sunrise_sunset) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sunrise_sunset);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(severity==null?"null":"" + severity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_time==null?"null":"" + start_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_time==null?"null":"" + end_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_lat==null?"null":start_lat.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_lng==null?"null":start_lng.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_lat==null?"null":end_lat.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_lng==null?"null":end_lng.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(distance_mi==null?"null":distance_mi.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(side==null?"null":side, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(city==null?"null":city, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(county==null?"null":county, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(state==null?"null":state, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(weather_timestamp==null?"null":"" + weather_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(temperature_f==null?"null":temperature_f.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_chill_f==null?"null":wind_chill_f.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(humidity_percent==null?"null":humidity_percent.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pressure_in==null?"null":pressure_in.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visibility_mi==null?"null":visibility_mi.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_speed_mph==null?"null":wind_speed_mph.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation_in==null?"null":precipitation_in.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(weather_condition==null?"null":weather_condition, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sunrise_sunset==null?"null":sunrise_sunset, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(severity==null?"null":"" + severity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_time==null?"null":"" + start_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_time==null?"null":"" + end_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_lat==null?"null":start_lat.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(start_lng==null?"null":start_lng.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_lat==null?"null":end_lat.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(end_lng==null?"null":end_lng.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(distance_mi==null?"null":distance_mi.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(side==null?"null":side, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(city==null?"null":city, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(county==null?"null":county, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(state==null?"null":state, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(weather_timestamp==null?"null":"" + weather_timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(temperature_f==null?"null":temperature_f.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_chill_f==null?"null":wind_chill_f.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(humidity_percent==null?"null":humidity_percent.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(pressure_in==null?"null":pressure_in.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visibility_mi==null?"null":visibility_mi.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_speed_mph==null?"null":wind_speed_mph.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation_in==null?"null":precipitation_in.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(weather_condition==null?"null":weather_condition, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sunrise_sunset==null?"null":sunrise_sunset, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.id = null; } else {
      this.id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.severity = null; } else {
      this.severity = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_time = null; } else {
      this.start_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_time = null; } else {
      this.end_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_lat = null; } else {
      this.start_lat = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_lng = null; } else {
      this.start_lng = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_lat = null; } else {
      this.end_lat = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_lng = null; } else {
      this.end_lng = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.distance_mi = null; } else {
      this.distance_mi = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.side = null; } else {
      this.side = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.city = null; } else {
      this.city = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.county = null; } else {
      this.county = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.state = null; } else {
      this.state = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.weather_timestamp = null; } else {
      this.weather_timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.temperature_f = null; } else {
      this.temperature_f = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_chill_f = null; } else {
      this.wind_chill_f = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.humidity_percent = null; } else {
      this.humidity_percent = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pressure_in = null; } else {
      this.pressure_in = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.visibility_mi = null; } else {
      this.visibility_mi = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_speed_mph = null; } else {
      this.wind_speed_mph = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.precipitation_in = null; } else {
      this.precipitation_in = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.weather_condition = null; } else {
      this.weather_condition = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.sunrise_sunset = null; } else {
      this.sunrise_sunset = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.id = null; } else {
      this.id = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.severity = null; } else {
      this.severity = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_time = null; } else {
      this.start_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_time = null; } else {
      this.end_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_lat = null; } else {
      this.start_lat = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.start_lng = null; } else {
      this.start_lng = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_lat = null; } else {
      this.end_lat = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.end_lng = null; } else {
      this.end_lng = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.distance_mi = null; } else {
      this.distance_mi = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.side = null; } else {
      this.side = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.city = null; } else {
      this.city = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.county = null; } else {
      this.county = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.state = null; } else {
      this.state = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.weather_timestamp = null; } else {
      this.weather_timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.temperature_f = null; } else {
      this.temperature_f = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_chill_f = null; } else {
      this.wind_chill_f = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.humidity_percent = null; } else {
      this.humidity_percent = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.pressure_in = null; } else {
      this.pressure_in = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.visibility_mi = null; } else {
      this.visibility_mi = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_speed_mph = null; } else {
      this.wind_speed_mph = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.precipitation_in = null; } else {
      this.precipitation_in = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.weather_condition = null; } else {
      this.weather_condition = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.sunrise_sunset = null; } else {
      this.sunrise_sunset = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    codegen_accidents o = (codegen_accidents) super.clone();
    o.start_time = (o.start_time != null) ? (java.sql.Timestamp) o.start_time.clone() : null;
    o.end_time = (o.end_time != null) ? (java.sql.Timestamp) o.end_time.clone() : null;
    o.weather_timestamp = (o.weather_timestamp != null) ? (java.sql.Timestamp) o.weather_timestamp.clone() : null;
    return o;
  }

  public void clone0(codegen_accidents o) throws CloneNotSupportedException {
    o.start_time = (o.start_time != null) ? (java.sql.Timestamp) o.start_time.clone() : null;
    o.end_time = (o.end_time != null) ? (java.sql.Timestamp) o.end_time.clone() : null;
    o.weather_timestamp = (o.weather_timestamp != null) ? (java.sql.Timestamp) o.weather_timestamp.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("severity", this.severity);
    __sqoop$field_map.put("start_time", this.start_time);
    __sqoop$field_map.put("end_time", this.end_time);
    __sqoop$field_map.put("start_lat", this.start_lat);
    __sqoop$field_map.put("start_lng", this.start_lng);
    __sqoop$field_map.put("end_lat", this.end_lat);
    __sqoop$field_map.put("end_lng", this.end_lng);
    __sqoop$field_map.put("distance_mi", this.distance_mi);
    __sqoop$field_map.put("side", this.side);
    __sqoop$field_map.put("city", this.city);
    __sqoop$field_map.put("county", this.county);
    __sqoop$field_map.put("state", this.state);
    __sqoop$field_map.put("weather_timestamp", this.weather_timestamp);
    __sqoop$field_map.put("temperature_f", this.temperature_f);
    __sqoop$field_map.put("wind_chill_f", this.wind_chill_f);
    __sqoop$field_map.put("humidity_percent", this.humidity_percent);
    __sqoop$field_map.put("pressure_in", this.pressure_in);
    __sqoop$field_map.put("visibility_mi", this.visibility_mi);
    __sqoop$field_map.put("wind_speed_mph", this.wind_speed_mph);
    __sqoop$field_map.put("precipitation_in", this.precipitation_in);
    __sqoop$field_map.put("weather_condition", this.weather_condition);
    __sqoop$field_map.put("sunrise_sunset", this.sunrise_sunset);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("severity", this.severity);
    __sqoop$field_map.put("start_time", this.start_time);
    __sqoop$field_map.put("end_time", this.end_time);
    __sqoop$field_map.put("start_lat", this.start_lat);
    __sqoop$field_map.put("start_lng", this.start_lng);
    __sqoop$field_map.put("end_lat", this.end_lat);
    __sqoop$field_map.put("end_lng", this.end_lng);
    __sqoop$field_map.put("distance_mi", this.distance_mi);
    __sqoop$field_map.put("side", this.side);
    __sqoop$field_map.put("city", this.city);
    __sqoop$field_map.put("county", this.county);
    __sqoop$field_map.put("state", this.state);
    __sqoop$field_map.put("weather_timestamp", this.weather_timestamp);
    __sqoop$field_map.put("temperature_f", this.temperature_f);
    __sqoop$field_map.put("wind_chill_f", this.wind_chill_f);
    __sqoop$field_map.put("humidity_percent", this.humidity_percent);
    __sqoop$field_map.put("pressure_in", this.pressure_in);
    __sqoop$field_map.put("visibility_mi", this.visibility_mi);
    __sqoop$field_map.put("wind_speed_mph", this.wind_speed_mph);
    __sqoop$field_map.put("precipitation_in", this.precipitation_in);
    __sqoop$field_map.put("weather_condition", this.weather_condition);
    __sqoop$field_map.put("sunrise_sunset", this.sunrise_sunset);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
