package az.portfolioapi.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}




//@Configuration
//public class ModelMapperConfig {
//
//    @Autowired
//    private PortfolioRepository portfolioRepository;
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        // Skill Converter
//        Converter<SkillRequestDTO, Skill> skillConverter = new Converter<SkillRequestDTO, Skill>() {
//            @Override
//            public Skill convert(MappingContext<SkillRequestDTO, Skill> context) {
//                SkillRequestDTO source = context.getSource();
//                Skill destination = context.getDestination();
//
//                Portfolio portfolio = portfolioRepository.findById(source.getPortfolioId())
//                        .orElseThrow(() -> new RuntimeException("Portfolio not found with ID: " + source.getPortfolioId()));
//                destination.setPortfolio(portfolio);
//                return destination;
//            }
//        };
//        modelMapper.addConverter(skillConverter);
//
//        // Project Converter
//        Converter<ProjectRequestDTO, Project> projectConverter = new Converter<ProjectRequestDTO, Project>() {
//            @Override
//            public Project convert(MappingContext<ProjectRequestDTO, Project> context) {
//                ProjectRequestDTO source = context.getSource();
//                Project destination = context.getDestination();
//
//                Portfolio portfolio = portfolioRepository.findById(source.getPortfolioId())
//                        .orElseThrow(() -> new RuntimeException("Portfolio not found with ID: " + source.getPortfolioId()));
//                destination.setPortfolio(portfolio);
//                return destination;
//            }
//        };
//        modelMapper.addConverter(projectConverter);
//
//        // Experience Converter
//        Converter<ExperienceRequestDTO, Experience> experienceConverter = new Converter<ExperienceRequestDTO, Experience>() {
//            @Override
//            public Experience convert(MappingContext<ExperienceRequestDTO, Experience> context) {
//                ExperienceRequestDTO source = context.getSource();
//                Experience destination = context.getDestination();
//
//                Portfolio portfolio = portfolioRepository.findById(source.getPortfolioId())
//                        .orElseThrow(() -> new RuntimeException("Portfolio not found with ID: " + source.getPortfolioId()));
//                destination.setPortfolio(portfolio);
//                return destination;
//            }
//        };
//        modelMapper.addConverter(experienceConverter);
//
//        // Education Converter
//        Converter<EducationRequestDTO, Education> educationConverter = new Converter<EducationRequestDTO, Education>() {
//            @Override
//            public Education convert(MappingContext<EducationRequestDTO, Education> context) {
//                EducationRequestDTO source = context.getSource();
//                Education destination = context.getDestination();
//
//                Portfolio portfolio = portfolioRepository.findById(source.getPortfolioId())
//                        .orElseThrow(() -> new RuntimeException("Portfolio not found with ID: " + source.getPortfolioId()));
//                destination.setPortfolio(portfolio);
//                return destination;
//            }
//        };
//        modelMapper.addConverter(educationConverter);
//
//        return modelMapper;
//    }
//}