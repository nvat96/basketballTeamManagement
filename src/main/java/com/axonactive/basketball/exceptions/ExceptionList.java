package com.axonactive.basketball.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionList extends Exception{
    private static final String ARENA_NOT_FOUND_MSG_KEY = "ArenaNotExisted";
    private static final String ARENA_NOT_FOUND_MSG = "Arena not found";
    private static final String AGENT_NOT_FOUND_MSG_KEY = "AgentNotExisted";
    private static final String AGENT_NOT_FOUND_MSG = "Agent not found";
    private static final String AGENT_CONTRACT_NOT_FOUND_MSG_KEY = "AgentContractNotExisted";
    private static final String AGENT_CONTRACT_NOT_FOUND_MSG = "Agent contract not found";
    private static final String COACH_NOT_FOUND_MSG_KEY = "CoachNotExisted";
    private static final String COACH_NOT_FOUND_MSG = "Coach not found";
    private static final String COACH_ACHIEVEMENT_NOT_FOUND_MSG_KEY = "CoachAchievementNotExisted";
    private static final String COACH_ACHIEVEMENT_NOT_FOUND_MSG = "Coach achievement not found";
    private static final String COACH_CONTRACT_NOT_FOUND_MSG_KEY = "CoachContractNotExisted";
    private static final String COACH_CONTRACT_NOT_FOUND_MSG = "Coach contract not found";
    private static final String OWNER_NOT_FOUND_MSG_KEY = "OwnerNotExisted";
    private static final String OWNER_NOT_FOUND_MSG = "Owner not found";
    private static final String OWNING_CERTIFICATE_NOT_FOUND_MSG_KEY = "OwningCertificateNotExisted";
    private static final String OWNING_CERTIFICATE_NOT_FOUND_MSG = "Owning certificate not found";
    private static final String PLAYER_NOT_FOUND_MSG_KEY = "PlayerNotExisted";
    private static final String PLAYER_NOT_FOUND_MSG = "Player not found";
    private static final String PLAYER_ACHIEVEMENT_NOT_FOUND_MSG_KEY = "PlayerAchievementNotExisted";
    private static final String PLAYER_ACHIEVEMENT_NOT_FOUND_MSG = "Player achievement not found";
    private static final String PLAYER_CONTRACT_NOT_FOUND_MSG_KEY = "PlayerContractNotExisted";
    private static final String PLAYER_CONTRACT_NOT_FOUND_MSG = "Player contract not found";
    private static final String STAFF_NOT_FOUND_MSG_KEY = "StaffNotExisted";
    private static final String STAFF_NOT_FOUND_MSG = "Staff not found";
    private static final String STATS_NOT_FOUND_MSG_KEY = "StatsNotExisted";
    private static final String STATS_NOT_FOUND_MSG = "Stats not found";
    private static final String STATUS_REPORT_NOT_FOUND_MSG_KEY = "StatusReportNotExisted";
    private static final String STATUS_REPORT_NOT_FOUND_MSG = "Status report not found";
    private static final String TEAM_NOT_FOUND_MSG_KEY = "TeamNotExisted";
    private static final String TEAM_NOT_FOUND_MSG = "Team not found";
    private static final String TEAM_ACHIEVEMENT_NOT_FOUND_MSG_KEY = "TeamAchievementNotExisted";
    private static final String TEAM_ACHIEVEMENT_NOT_FOUND_MSG = "Team achievement not found";
    public static ResponseException notFound(String messageKey, String message){
        return new ResponseException(messageKey,message, HttpStatus.NOT_FOUND);
    }
    public static ResponseException badRequest(String messageKey, String message){
        return new ResponseException(messageKey,message, HttpStatus.BAD_REQUEST);
    }
    public static ResponseException internalServerError(String messageKey, String message){
        return new ResponseException(messageKey,message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public static ResponseException arenaNotFound(){
        return notFound(ARENA_NOT_FOUND_MSG_KEY, ARENA_NOT_FOUND_MSG);
    }
    public static ResponseException agentNotFound(){
        return notFound(AGENT_NOT_FOUND_MSG_KEY, AGENT_NOT_FOUND_MSG);
    }
    public static ResponseException agentContractNotFound(){
        return notFound(AGENT_CONTRACT_NOT_FOUND_MSG_KEY, AGENT_CONTRACT_NOT_FOUND_MSG);
    }
    public static ResponseException coachNotFound(){
        return notFound(COACH_NOT_FOUND_MSG_KEY, COACH_NOT_FOUND_MSG);
    }
    public static ResponseException coachAchievementNotFound(){
        return notFound(COACH_ACHIEVEMENT_NOT_FOUND_MSG_KEY, COACH_ACHIEVEMENT_NOT_FOUND_MSG);
    }
    public static ResponseException coachContractNotFound(){
        return notFound(COACH_CONTRACT_NOT_FOUND_MSG_KEY, COACH_CONTRACT_NOT_FOUND_MSG);
    }
    public static ResponseException ownerNotFound(){
        return notFound(OWNER_NOT_FOUND_MSG_KEY, OWNER_NOT_FOUND_MSG);
    }
    public static ResponseException owningCertificateNotFound(){
        return notFound(OWNING_CERTIFICATE_NOT_FOUND_MSG_KEY, OWNING_CERTIFICATE_NOT_FOUND_MSG);
    }
    public static ResponseException playerNotFound(){
        return notFound(PLAYER_NOT_FOUND_MSG_KEY, PLAYER_NOT_FOUND_MSG);
    }
    public static ResponseException playerAchievementNotFound(){
        return notFound(PLAYER_ACHIEVEMENT_NOT_FOUND_MSG_KEY, PLAYER_ACHIEVEMENT_NOT_FOUND_MSG);
    }
    public static ResponseException playerContractNotFound(){
        return notFound(PLAYER_CONTRACT_NOT_FOUND_MSG_KEY, PLAYER_CONTRACT_NOT_FOUND_MSG);
    }
    public static ResponseException staffNotFound(){
        return notFound(STAFF_NOT_FOUND_MSG_KEY, STAFF_NOT_FOUND_MSG);
    }
    public static ResponseException statsNotFound(){
        return notFound(STATS_NOT_FOUND_MSG_KEY, STATS_NOT_FOUND_MSG);
    }
    public static ResponseException statusReportNotFound(){
        return notFound(STATUS_REPORT_NOT_FOUND_MSG_KEY, STATUS_REPORT_NOT_FOUND_MSG);
    }
    public static ResponseException teamNotFound(){
        return notFound(TEAM_NOT_FOUND_MSG_KEY, TEAM_NOT_FOUND_MSG);
    }
    public static ResponseException teamAchievementNotFound(){
        return notFound(TEAM_ACHIEVEMENT_NOT_FOUND_MSG_KEY, TEAM_ACHIEVEMENT_NOT_FOUND_MSG);
    }

}
