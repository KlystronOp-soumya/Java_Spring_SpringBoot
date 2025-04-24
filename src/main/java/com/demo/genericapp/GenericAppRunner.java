package com.demo.genericapp;

import java.time.LocalDate;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.demo.genericapp.configs.configprops.OracleDbConfigProps;
import com.demo.genericapp.entity.LOVModel;
import com.demo.genericapp.entity.dto.LOVDTO;

@Component
public class GenericAppRunner implements ApplicationRunner {

	private transient GenericAppContext appContext;

	public GenericAppRunner(final GenericAppContext appContext) {
		this.appContext = appContext;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Running this app");

		if (null != appContext) {
			OracleDbConfigProps oracleDbConfigProps = (OracleDbConfigProps) appContext.getBean();
			System.out.println(oracleDbConfigProps.getDriverclass());
			System.out.println(oracleDbConfigProps.getUser());

			ModelMapper modelMapper = new ModelMapper();

			modelMapper.getConfiguration().setAmbiguityIgnored(false).setFieldMatchingEnabled(true)
					.setMatchingStrategy(MatchingStrategies.STRICT).setFieldAccessLevel(AccessLevel.PRIVATE);

			/*
			 * Converter<String, String> toUppercase = new AbstractConverter<String,
			 * String>() { protected String convert(String source) { return source == null ?
			 * null : source.toUppercase(); } };
			 * 
			 * using(toUppercase).map(source.name, destination.name);
			 * 
			 * When defining a mapping to use this converter, we simply pass the source
			 * object, which is of type Person, to the map method:
			 * 
			 * using(personToNameConverter).map(source).setName(null); Note: Since a source
			 * object is given, the null value passed to setName is unused.
			 * 
			 */
			Converter<Date, java.sql.Date> dateConverter1 = new AbstractConverter<Date, java.sql.Date>() {
				@Override
				protected java.sql.Date convert(Date source) {
					return (source != null) ? new java.sql.Date(source.getTime()) : null;
				}
			};

			// Custom Converter
			Converter<java.util.Date, java.sql.Date> dateConverter = new Converter<java.util.Date, java.sql.Date>() {
				@Override
				public java.sql.Date convert(MappingContext<java.util.Date, java.sql.Date> context) {
					return (context.getSource() != null) ? new java.sql.Date(context.getSource().getTime()) : null;
				}
			};

			// Uncomment the below code it is working fine
			/*
			 * TypeMap<LOVModel, LOVDTO> typeMap = modelMapper.createTypeMap(LOVModel.class,
			 * LOVDTO.class); typeMap.addMappings(new PropertyMap<LOVModel, LOVDTO>() {
			 * 
			 * @Override protected void configure() { using((ctx) -> { String codeStr =
			 * ((LOVModel) ctx.getSource()).getCode(); return (codeStr != null &&
			 * !codeStr.isEmpty()) ? Integer.parseInt(codeStr) : null;
			 * }).map(source).setCode(0);
			 * 
			 * // skip().setName(null); } });
			 */ /*
				 * .addMappings(mapper -> { mapper.using(dateConverter).map(LOVModel::getDate,
				 * LOVDTO::setDbDate); });
				 */

			TypeMap<LOVModel, LOVDTO> typeMap2 = modelMapper.createTypeMap(LOVModel.class, LOVDTO.class);
			typeMap2.addMappings((mapper) -> mapper.using((ctx) -> {
				String codeStr = ((LOVModel) ctx.getSource()).getCode();
				return (codeStr != null && !codeStr.isEmpty()) ? Integer.parseInt(codeStr) : null;
			})).addMappings(mapper -> {
				mapper.using(dateConverter).map(LOVModel::getDate, LOVDTO::setDbDate);
			});

			/*
			 * modelMapper.addMappings(new PropertyMap<LOVModel, LOVDTO>() {
			 * 
			 * @Override protected void configure() { using((ctx) -> { String codeStr =
			 * ((LOVModel) ctx.getSource()).getCode(); return (codeStr != null &&
			 * !codeStr.isEmpty()) ? Integer.parseInt(codeStr) : null;
			 * }).map(source).setCode(0);
			 * 
			 * // skip().setName(null); } });
			 */

			LOVModel lovModel = new LOVModel();
			LOVDTO lovdto = new LOVDTO();

			lovModel.setCode("10008");
			lovModel.setName("System");
			lovModel.setDate(java.sql.Date.valueOf(LocalDate.now()));

			modelMapper.map(lovModel, lovdto);
			System.out.println(lovdto);

		}
	}

}
