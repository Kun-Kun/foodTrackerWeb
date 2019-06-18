package ua.epam.food.dto;

import lombok.Data;
import ua.epam.food.dao.entity.DietEntity;
import ua.epam.food.dao.entity.GoalEntity;
import ua.epam.food.dao.entity.PhysicalLoadLevelEntity;
import ua.epam.food.dao.entity.SexEntity;

import java.util.List;

@Data
public class ProfileSelectable extends Profile{

    private List<DietEntity> dietList;
    private List<GoalEntity> goalList;
    private List<PhysicalLoadLevelEntity> physicalLoadLevelList;
    private List<SexEntity> sexList;
}
