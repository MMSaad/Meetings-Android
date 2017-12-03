package sa.gov.hajj.meetings.interfaces;


import sa.gov.hajj.meetings.webServices.viewModels.ValidationResult;

public interface IViewModel {
    ValidationResult validate();

    String serviceFormat();
}
