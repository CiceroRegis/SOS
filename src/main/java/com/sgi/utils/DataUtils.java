package com.sgi.utils;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataUtils {
	
	public static final String FORMATO_DATA_DD_MM_AAAA_BARRA = "dd/MM/yyyy";
	public static final String FORMATO_DATA_DD_MM_AAAA_TRACO = "dd-MM-yyyy";
    public static final String FORMATO_DATA_AAAA_MM_DD_BARRA = "yyyy/MM/dd";
    public static final String FORMATO_DATA_DD_MM_AAAA = "ddMMyyyy";
    public static final String FORMATO_DATA_AAAA_MM_DD = "yyyyMMdd";
    public static final String FORMATO_HORA_HH_MM_SS = "HHmmss";
    public static final String FORMATO_DATA_HORA_COMPLETO = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMATO_DATA_HORA_COMPLETO_SQL_TRACO = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_HORA_SIMPLES = "HH:mm";
    public static final String FORMATO_HORA_COMPLETO = "HH:mm:ss";
    public static final String FORMATO_DATA_SQL_TRACO = "yyyy-MM-dd";
    public static final String FORMATO_DATA_AAA = "yyyy";
    public static final String FORMATO_DATA_MM = "MM";
    public static final String FORMATO_DATA_DD = "dd";
    
    
    
    public static LocalDate convertToLocalDate(Date data){
    	if(data != null){
		    ZoneId defaultZoneId = ZoneId.systemDefault();
		    Instant instant = data.toInstant();    	       
	        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
	        return localDate;
    	}else{
    		return null;
    	}
    }
    
    public static LocalDate convertToLocalDate(java.sql.Date data){
    	if(data != null){
    		return data.toLocalDate();
    	}else{
    		return null;
    	}
    }
    
    public static LocalDateTime convertToLocalDateTime(Timestamp data){
    	if(data != null){
    		return data.toLocalDateTime();
    	}else{
    		return null;
    	}
    }
    
    public static java.sql.Date convertToDate(LocalDate data){
    	java.sql.Date date = java.sql.Date.valueOf(data);
    	return date;
    }
    
    public static Time convertToTime(LocalTime localTime){
    	Time time = Time.valueOf(localTime);
    	return time;
    }
    
    public static Timestamp convertToTimestamp(LocalDateTime localDateTime){
    	Timestamp timestamp = Timestamp.valueOf(localDateTime);
    	return timestamp;
    }
    
    public static LocalTime convertToLocalTime(Time time){
    	if(time !=null){
    	return time.toLocalTime();
    	}else{
    		return null;
    	}
    }
		
    public static LocalDate getDataAtual() {
    	return LocalDate.now();
    }
    
    public static int getAnoAtual() {
    	return LocalDate.now().getYear();
    }
    
    public static LocalDateTime getTimestampAtual() {
    	return LocalDateTime.now();
    }
    
    public static LocalTime getHoraAtual() {
    	return LocalTime.now();
    }
    
    public static LocalDate calcularPrazo(LocalDate data, int dias) {
    	return data.plusDays(dias);
    }
    
    public static LocalDate calcularPrazo(LocalDateTime data, int dias) {
    	return data.plusDays(dias).toLocalDate();
    }
    
    public static Integer calcularDiferencaDias(LocalDate dataInicial, LocalDate dataFinal) {
    	Long dias = dataInicial.until(dataFinal, ChronoUnit.DAYS);    	
    	return dias.intValue();
    }
    
    public static Integer calcularDiferencaDiasHoje(LocalDate dataFinal) {
    	Long dias = LocalDate.now().until(dataFinal, ChronoUnit.DAYS);    	
    	return dias.intValue();
    }
    
    public static String formatarData(LocalDate data, String pattern) {
    	return data.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    public static String formatarData(LocalDateTime data, String pattern) {
    	return data.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    public static String formatarHora(LocalTime time, String pattern) {
    	return time.format(DateTimeFormatter.ofPattern(pattern));
    }
	
    //Metodos de Parse    
    public static LocalDate parse(String str, String pattern){    	
    	return LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern));
    }
    
    public static LocalDateTime parseDateTime(String str, String pattern){
    	return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }
    
    public static LocalTime parseTime(String str, String pattern){
    	return LocalTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }
    
    public static String clean(final String data) {
		if(data != null && !data.equals("")){
		String dataLimpo = data.trim();
		dataLimpo = dataLimpo.replaceAll("\\/", "");
		dataLimpo = dataLimpo.replaceAll("\\-", "");
		dataLimpo = dataLimpo.replaceAll(" ", "");
		return dataLimpo;
		}else{
			return null;
		}
	}
    
    /**
     * Retorna uma String com a data a partir do pattern informado.
     * 
     * @author Wenderson
     * @param data
     *            Uma data para ser formatada.
     * @param pattern
     *            O pattern no qual se deseja o formato da data.
     * @return Uma String com a data a partir do pattern informado. Se data ou
     *         pattern for null, uma String vazia será retornada.
     */
    public static String formatarData(Date data, String pattern) {
        String stData = "";
        if ((data != null) && (pattern != null)) {
            DateFormat format = new SimpleDateFormat(pattern);
            stData = format.format(data);
        }
        return stData;
    }

}
