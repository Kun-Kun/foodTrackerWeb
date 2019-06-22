package ua.tracker.food.dto;

import lombok.Data;
import ua.tracker.food.component.Gender;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PhysicalLoadLevelEntity;

import java.util.List;

@Data
public class ProfileSelectable extends Profile{

    private List<DietEntity> dietList;
    private List<GoalEntity> goalList;
    private List<PhysicalLoadLevelEntity> physicalLoadLevelList;
    private List<Gender> genderList;
}
