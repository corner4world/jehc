package jehc.paymodules.paypal.bean.order;

import com.alibaba.fastjson.annotation.JSONField;
public class Phone{
	/**
	 * Country code (from in E.164 format)
	 */
	@JSONField(name = "country_code")
	private String countryCode;
	/**
	 * In-country phone number (from in E.164 format)
	 */
	@JSONField(name = "national_number")
	private String nationalNumber;
	/**
	 * Phone extension
	 */
	private String extension;

	/**
	 * Default Constructor
	 */
	public Phone() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Phone(String countryCode, String nationalNumber) {
		this.countryCode = countryCode;
		this.nationalNumber = nationalNumber;
	}

	/**
	 * Country code (from in E.164 format)
	 */
	@SuppressWarnings("all")
	public String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * In-country phone number (from in E.164 format)
	 */
	@SuppressWarnings("all")
	public String getNationalNumber() {
		return this.nationalNumber;
	}

	/**
	 * Phone extension
	 */
	@SuppressWarnings("all")
	public String getExtension() {
		return this.extension;
	}

	/**
	 * Country code (from in E.164 format)
	 * @return this
	 */
	@SuppressWarnings("all")
	public Phone setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	/**
	 * In-country phone number (from in E.164 format)
	 * @return this
	 */
	@SuppressWarnings("all")
	public Phone setNationalNumber(final String nationalNumber) {
		this.nationalNumber = nationalNumber;
		return this;
	}

	/**
	 * Phone extension
	 * @return this
	 */
	@SuppressWarnings("all")
	public Phone setExtension(final String extension) {
		this.extension = extension;
		return this;
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Phone)) return false;
		final Phone other = (Phone) o;
		if (!other.canEqual((Object) this)) return false;
		if (!super.equals(o)) return false;
		final Object this$countryCode = this.getCountryCode();
		final Object other$countryCode = other.getCountryCode();
		if (this$countryCode == null ? other$countryCode != null : !this$countryCode.equals(other$countryCode)) return false;
		final Object this$nationalNumber = this.getNationalNumber();
		final Object other$nationalNumber = other.getNationalNumber();
		if (this$nationalNumber == null ? other$nationalNumber != null : !this$nationalNumber.equals(other$nationalNumber)) return false;
		final Object this$extension = this.getExtension();
		final Object other$extension = other.getExtension();
		if (this$extension == null ? other$extension != null : !this$extension.equals(other$extension)) return false;
		return true;
	}

	@SuppressWarnings("all")
	protected boolean canEqual(final Object other) {
		return other instanceof Phone;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		result = result * PRIME + super.hashCode();
		final Object $countryCode = this.getCountryCode();
		result = result * PRIME + ($countryCode == null ? 43 : $countryCode.hashCode());
		final Object $nationalNumber = this.getNationalNumber();
		result = result * PRIME + ($nationalNumber == null ? 43 : $nationalNumber.hashCode());
		final Object $extension = this.getExtension();
		result = result * PRIME + ($extension == null ? 43 : $extension.hashCode());
		return result;
	}
}
