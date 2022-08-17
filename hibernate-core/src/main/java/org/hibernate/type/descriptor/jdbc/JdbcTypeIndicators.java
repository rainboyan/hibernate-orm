/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.type.descriptor.jdbc;

import java.sql.Types;
import jakarta.persistence.EnumType;
import jakarta.persistence.TemporalType;

import org.hibernate.TimeZoneStorageStrategy;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.java.BasicJavaType;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;
import org.hibernate.type.spi.TypeConfiguration;

/**
 * More-or-less a parameter-object intended for use in determining the SQL/JDBC type recommended
 * by the JDBC spec (explicitly or implicitly) for a given Java type.
 *
 * @see BasicJavaType#getRecommendedJdbcType
 *
 * @author Steve Ebersole
 */
public interface JdbcTypeIndicators {
	int NO_COLUMN_LENGTH = -1;
	int NO_COLUMN_PRECISION = -1;
	int NO_COLUMN_SCALE = -1;

	/**
	 * Was nationalized character datatype requested for the given Java type?
	 *
	 * @return {@code true} if nationalized character datatype should be used; {@code false} otherwise.
	 */
	default boolean isNationalized() {
		return false;
	}

	/**
	 * Was LOB datatype requested for the given Java type?
	 *
	 * @return {@code true} if LOB datatype should be used; {@code false} otherwise.
	 */
	default boolean isLob() {
		return false;
	}

	/**
	 * For enum mappings, what style of storage was requested (name vs. ordinal)?
	 *
	 * @return The enum type.
	 */
	default EnumType getEnumeratedType() {
		return EnumType.ORDINAL;
	}

	/**
	 * For temporal type mappings, what precision was requested?
	 */
	default TemporalType getTemporalPrecision() {
		return null;
	}

	/**
	 * When mapping a boolean type to the database what is the preferred SQL type code to use?
	 * <p/>
	 * Specifically names the key into the
	 * {@link JdbcTypeRegistry}.
	 */
	default int getPreferredSqlTypeCodeForBoolean() {
		return getTypeConfiguration().getCurrentBaseSqlTypeIndicators().getPreferredSqlTypeCodeForBoolean();
	}

	/**
	 * When mapping a duration type to the database what is the preferred SQL type code to use?
	 * <p/>
	 * Specifically names the key into the
	 * {@link JdbcTypeRegistry}.
	 */
	default int getPreferredSqlTypeCodeForDuration() {
		return getTypeConfiguration().getCurrentBaseSqlTypeIndicators().getPreferredSqlTypeCodeForDuration();
	}

	/**
	 * When mapping an uuid type to the database what is the preferred SQL type code to use?
	 * <p/>
	 * Specifically names the key into the
	 * {@link JdbcTypeRegistry}.
	 */
	default int getPreferredSqlTypeCodeForUuid() {
		return getTypeConfiguration().getCurrentBaseSqlTypeIndicators().getPreferredSqlTypeCodeForUuid();
	}

	/**
	 * When mapping an instant type to the database what is the preferred SQL type code to use?
	 * <p/>
	 * Specifically names the key into the
	 * {@link JdbcTypeRegistry}.
	 */
	default int getPreferredSqlTypeCodeForInstant() {
		return getTypeConfiguration().getCurrentBaseSqlTypeIndicators().getPreferredSqlTypeCodeForInstant();
	}

	/**
	 * When mapping a basic array or collection type to the database what is the preferred SQL type code to use?
	 * <p/>
	 * Specifically names the key into the
	 * {@link JdbcTypeRegistry}.
	 * @since 6.1
	 */
	default int getPreferredSqlTypeCodeForArray() {
		return getTypeConfiguration().getCurrentBaseSqlTypeIndicators().getPreferredSqlTypeCodeForArray();
	}

	/**
	 * Useful for resolutions based on column length.  E.g. choosing between a VARCHAR (String) and a CHAR(1) (Character/char)
	 */
	default long getColumnLength() {
		return NO_COLUMN_LENGTH;
	}

	/**
	 * Useful for resolutions based on column precision.
	 */
	default int getColumnPrecision() {
		return NO_COLUMN_PRECISION;
	}

	/**
	 * Useful for resolutions based on column scale. E.g. choosing between a NUMERIC or INTERVAL
	 */
	default int getColumnScale() {
		return NO_COLUMN_SCALE;
	}

	default TimeZoneStorageStrategy getDefaultTimeZoneStorageStrategy() {
		return getTypeConfiguration().getCurrentBaseSqlTypeIndicators().getDefaultTimeZoneStorageStrategy();
	}

	/**
	 * Provides access to the TypeConfiguration for access to various type-system registries.
	 */
	TypeConfiguration getTypeConfiguration();
}