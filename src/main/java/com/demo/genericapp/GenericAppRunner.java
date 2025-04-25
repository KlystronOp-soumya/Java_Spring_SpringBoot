package com.demo.genericapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.demo.genericapp.configs.configprops.OracleDbConfigProps;
import com.demo.genericapp.entity.dto.LOVDTO;
import com.demo.genericapp.entity.dto.MPSBCriteriaServcDTO;
import com.demo.genericapp.entity.dto.MPSBSearchResultDTO;
import com.demo.genericapp.model.LOVModel;
import com.demo.genericapp.model.MPSBCriteraServcModel;
import com.demo.genericapp.model.MPSBSearchResultModel;

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

			// mapping the MPSBCriteriaSrvcDTO to Model
			MPSBCriteriaServcDTO mpsbCriteriaServcDTO = new MPSBCriteriaServcDTO();
			mpsbCriteriaServcDTO.setPropA("DTOA");
			mpsbCriteriaServcDTO.setPropB("DTOB");
			List<MPSBSearchResultDTO> searchResultDTOs = new ArrayList<>();
			searchResultDTOs.add(new MPSBSearchResultDTO(1, new BigDecimal(12.3).setScale(2, RoundingMode.HALF_UP),
					new BigDecimal(5.5).setScale(2, RoundingMode.HALF_UP), 'Y'));
			searchResultDTOs.add(new MPSBSearchResultDTO(2, new BigDecimal(10.6).setScale(2, RoundingMode.HALF_UP),
					new BigDecimal(3.5).setScale(2, RoundingMode.HALF_UP), 'N'));
			searchResultDTOs.add(new MPSBSearchResultDTO(3, new BigDecimal(9.3).setScale(2, RoundingMode.HALF_UP),
					new BigDecimal(0.5).setScale(2, RoundingMode.HALF_UP), 'N'));

			mpsbCriteriaServcDTO.setMpsbSearchResultDTOs(searchResultDTOs);

			MPSBCriteraServcModel mpsbCriteraServcModel = new MPSBCriteraServcModel();
			mpsbCriteraServcModel.setPropA("MODELA");
			mpsbCriteraServcModel.setPropB("MODELB");

			MPSBSrvcCrtDtoToModelMapper mapper = new MPSBSrvcCrtDtoToModelMapper();
			modelMapper.addMappings(mapper).map(mpsbCriteriaServcDTO, mpsbCriteraServcModel);
			System.out.println(mpsbCriteraServcModel);

			System.out.println("====> Showing the search result grid");

			mpsbCriteraServcModel.getSearchResultModels().stream().forEach(System.out::println);

		}
	}

	public static class MPSBSrvcCrtDtoToModelMapper extends PropertyMap<MPSBCriteriaServcDTO, MPSBCriteraServcModel> {

		@Override
		protected void configure() {
			/*
			 * // Use conditionally for specific mappings when(context ->
			 * context.getSource() != null).map().setPropA(source.getSomeValue());
			 * when(context -> context.getSource() != null).map().setPropB(source.getA
			 */
			// or
			/*
			 * typeMap.addMappings(mapper -> { // Add a condition locally to map only
			 * specific properties mapper.when(context -> { String propertyName =
			 * context.getMapping().getLastDestinationProperty().getName(); return
			 * propertyName.equals("propA") || propertyName.equals("propB");
			 * }).map(SourceClass::getSourceProperty,
			 * DestinationClass::setDestinationProperty); });
			 */
			using((context) -> {
				List<MPSBSearchResultDTO> sourceSearchResultDTOs = (List<MPSBSearchResultDTO>) context.getSource();
				if (sourceSearchResultDTOs != null) {
					// both the codes works simply fine
					/*
					 * List<MPSBSearchResultModel> desList = new ArrayList<>(); for
					 * (MPSBSearchResultDTO dto : sourceSearchResultDTOs) { desList.add(new
					 * MPSBSearchResultModel(dto.getId(), dto.getMinPercent().toString(),
					 * dto.getPsRate().toString(), dto.getIndicator(), null)); } return desList;
					 */
					return sourceSearchResultDTOs.stream()
							.map(eachResultDto -> new MPSBSearchResultModel(eachResultDto.getId(),
									eachResultDto.getMinPercent().toString(), eachResultDto.getPsRate().toString(),
									eachResultDto.getIndicator(), null))
							.toList();
				}
				return new ArrayList<>(); // Handle null or empty case
			}).map(source.getMpsbSearchResultDTOs(), destination.getSearchResultModels());

			skip(destination.getPropA());
			skip(destination.getPropB());
		}

	}

}
