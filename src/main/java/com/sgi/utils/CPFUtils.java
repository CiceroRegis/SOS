package com.sgi.utils;

/**
 * 
 * @author Cícero Régis in 28/09/2018
 *
 */
public class CPFUtils {

	/**
	 * Clean cpf
	 * @param cpf
	 * @return
	 */
	public static String clean(final String cpf) {
		if(cpf != null && ! cpf.equals("")){
		String cpfLimpo = cpf.trim();
		cpfLimpo = cpfLimpo.replaceAll("\\.", "");
		cpfLimpo = cpfLimpo.replaceAll("\\-", "");
		cpfLimpo = cpfLimpo.replaceAll("\\/", "");
		return cpfLimpo;
		}else{
			return null;
		}
		
	}

	/**
	 * Returns a formatted <code>String</code> of the given CPF. A fully valid
	 * CPF which passed the {@link CPFUtil#validate(String)} test is required.
	 * The output matches the format 999.999.999-99.
	 * 
	 * @param cpf
	 *            A full valid CPF.
	 * @return Formatted CPF <code>String</code>.
	 * @throws IllegalArgumentException
	 *             if the given CPF is not valid.
	 * @author Cícero Régis
	 */
	public static String format(String cpf) {
		if (!validate(cpf)) {
			throw new IllegalArgumentException("CPF Invalido " + cpf);
		}
		StringBuilder builder = new StringBuilder(cpf.replaceAll("[^\\d]", ""));
		builder.insert(3, '.');
		builder.insert(7, '.');
		builder.insert(11, '-');
		return builder.toString();
	}

	/**
	 * Generates a random full valid CPF. The purpose of this method is to help
	 * testing procedures by generating valid test data.
	 * 
	 * @return A full valid CPF <code>String</code>.
	 * @author Cícero Régis
	 */
	public static String generate() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			builder.append(Math.round(Math.random() * 9));
		}
		builder.append(getValidationDigits(builder.toString()));
		return builder.toString();
	}

	/**
	 * A formatted version of {@link CPFUtil#generate()}. The output is
	 * formatted according to {@link CPFUtil#format(String)}.
	 * 
	 * @return A formatted and full valid CPF <code>String</code>.
	 * @author Cícero Régis
	 */
	public static String generateFormatted() {
		return format(generate());
	}

	/**
	 * Calculate the validation digits based on the government defined method.
	 * 
	 * @param cpf
	 *            A format valid CPF with size between 9 (without validation
	 *            digits) and 11 (with validation digits). Existing validation
	 *            digits will be ignored.
	 * @return A string with two digits.
	 * @throws IllegalArgumentException
	 *             if input is null or not valid.
	 * @author Cícero Régis
	 */
	protected static String getValidationDigits(String cpf) {
		if (cpf == null || !cpf.matches("[\\d]{9,11}")) {
			throw new IllegalArgumentException("CPF nao e valido: " + cpf);
		}
		// calculate both digit
		int d1 = 0, d2 = 0;
		for (int i = 0; i < 9; i++) {
			d1 += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
			d2 += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
		}
		d1 = 11 - d1 % 11;
		d1 = (d1 > 9) ? 0 : d1;
		// complete using the previous calculated digit
		d2 += d1 * 2;
		d2 = 11 - d2 % 11;
		d2 = (d2 > 9) ? 0 : d2;
		return "" + d1 + d2;
	}
	
	
	/**
	 * 
	 * @param cpf
	 *   String valor a ser testado
	 * @return boolean indicado se usuário entrou com CPF padrao.
	 */
	private static boolean isCPFPadrao(String cpf){
		if(cpf.equals("00000000000")
		   || cpf.equals("11111111111")
		   || cpf.equals("22222222222")
		   || cpf.equals("33333333333")
		   || cpf.equals("44444444444")
		   || cpf.equals("55555555555")
		   || cpf.equals("66666666666")
		   || cpf.equals("77777777777")
		   || cpf.equals("88888888888")
		   || cpf.equals("99999999999")){
			return true;
		}
		return false;
	}
	

	/**
	 * Total validation of the CPF ignoring the format. All non-numeric
	 * characters will be ignored. Validation digits are tested as well.
	 * 
	 * @param cpf
	 *            The CPF to be tested.
	 * @return true if CPF is full valid.
	 * @author Cícero Régis
	 */
	public static boolean validate(String cpf) {
		if (cpf == null || isCPFPadrao(cpf)) {
			return false;
		}
		String _cpf = cpf.replaceAll("[^\\d]", "");
		if (!_cpf.matches("[\\d]{11}"))
			return false;
		return _cpf.equals(_cpf.substring(0, 9) + getValidationDigits(_cpf));
	}
}