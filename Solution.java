import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        
        final int MaxL = 100;
        final int MaxC = 100;
        char[][] Map = new char[MaxL][MaxC];
        String[][] VisitedSoberNonInverted = new String[MaxL][MaxC];
        String[][] VisitedDrunkNonInverted = new String[MaxL][MaxC];
        String[][] VisitedSoberInverted = new String[MaxL][MaxC];
        String[][] VisitedDrunkInverted = new String[MaxL][MaxC];

        // Set the initial values for the states that Bender may happen to be in
        for (int i = 0; i < MaxL; i++)
            for (int j = 0; j < MaxC; j++) {
                VisitedSoberNonInverted[i][j] = "";
                VisitedDrunkNonInverted[i][j] = "";
                VisitedSoberInverted[i][j] = "";
                VisitedDrunkInverted[i][j] = "";
            }


        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        int CurrentL = 0;
        int CurrentC = 0;
        int ProcessingCurrentL = 0;
        int ProcessingCurrentC = 0;
        int Teleport1L = 0;
        int Teleport1C = 0;
        int Teleport2L = 0;
        int Teleport2C = 0;
        char ProcessedCharacter = '\0';
        char CurrentDirection = 'S';
        String DirectionToPhoneBooth = "";
        Boolean ResetDirection = true;
        Boolean BreakerMode = false;
        Boolean IsInverted = false;
        
        if ( in.hasNextLine()) { in.nextLine(); }
        
        // Create map
        for (int i = 0; i < L; i++) {
            String row = in.nextLine();
            Map[i] = row.toCharArray();
            // Set starting points coordinates for Bender
            if (row.indexOf('@') > -1) {
                CurrentL = i;
                CurrentC = row.indexOf('@');
            }
            // Remember the coordinates where the teleporters are located on the map
            if (row.indexOf('T') > -1) {
                if (Teleport1L == 0) {
                    Teleport1L = i;
                    Teleport1C = row.indexOf('T');
                    if (row.indexOf('T', row.indexOf('T') + 1) > -1) {
                        Teleport2L = i;
                        Teleport2C = row.indexOf('T', row.indexOf('T') + 1);
                    }
                } else {
                    Teleport2L = i;
                    Teleport2C = row.indexOf('T');
                }
            }
            
            // Display the map.  The next line is for debugging purposes.
            //System.out.println("Map[0]: " + new String(Map[i]));
        }
        
        // Process Map: keep inside the loop until '$' is encountered or the code finds itself in a looping scenario
        while (ProcessedCharacter != '$') {
            
            ProcessingCurrentL = CurrentL;
            ProcessingCurrentC = CurrentC;

            // Keep moving in the current direction
            switch (CurrentDirection) {
                case 'N':
                    ProcessingCurrentL--;
                    break;
                case 'S':
                    ProcessingCurrentL++;
                    break;
                case 'W':
                    ProcessingCurrentC--;
                    break;
                case 'E':
                    ProcessingCurrentC++;
                    break;
            }            

            ProcessedCharacter = Map[ProcessingCurrentL][ProcessingCurrentC];

            // Check the new spot on the map
            switch (ProcessedCharacter) {
                case 'N':
                case 'S':
                case 'W':
                case 'E':
                    DirectionToPhoneBooth += (DirectionToPhoneBooth != "") ? "\n" + PrintDirection(CurrentDirection) : PrintDirection(CurrentDirection);
                    //System.out.println("Visited[CurrentL][CurrentC]: " + Visited[CurrentL][CurrentC] + "CurrentDirection: " + CurrentDirection);
                    if (!IsInverted) {
                        if (!BreakerMode) {
                            if (VisitedSoberNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    } else {
                        if (!BreakerMode) {
                            if (VisitedSoberInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    }
                    CurrentDirection = ProcessedCharacter;
                    CurrentL = ProcessingCurrentL;
                    CurrentC = ProcessingCurrentC;
                    break;
                case '$':
                case ' ':
                    DirectionToPhoneBooth += (DirectionToPhoneBooth != "") ? "\n" + PrintDirection(CurrentDirection) : PrintDirection(CurrentDirection);
                    if (!IsInverted) {
                        if (!BreakerMode) {
                            if (VisitedSoberNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    } else {
                        if (!BreakerMode) {
                            if (VisitedSoberInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    }
                    ResetDirection = true;
                    CurrentL = ProcessingCurrentL;
                    CurrentC = ProcessingCurrentC;
                    break;
                case '#':
                    ProcessedCharacter = Map[CurrentL][CurrentC];
                    CurrentDirection = SetCurrentDirection(CurrentDirection, ResetDirection, IsInverted);
                    ResetDirection = false;
                    break;
                case 'X':
                    if (!BreakerMode) {
                        ProcessedCharacter = Map[CurrentL][CurrentC];
                        CurrentDirection = SetCurrentDirection(CurrentDirection, ResetDirection, IsInverted);
                        ResetDirection = false;
                    } else {
                        DirectionToPhoneBooth += (DirectionToPhoneBooth != "") ? "\n" + PrintDirection(CurrentDirection) : PrintDirection(CurrentDirection);
                        if (!IsInverted) {
                            if (!BreakerMode) {
                                if (VisitedSoberNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                    DirectionToPhoneBooth = "LOOP";
                                    break;
                                } else {
                                    VisitedSoberNonInverted[CurrentL][CurrentC] += CurrentDirection;
                                }
                            } else {
                                if (VisitedDrunkNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                    DirectionToPhoneBooth = "LOOP";
                                    break;
                                } else {
                                    VisitedDrunkNonInverted[CurrentL][CurrentC] += CurrentDirection;
                                }
                            }
                        } else {
                            if (!BreakerMode) {
                                if (VisitedSoberInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                    DirectionToPhoneBooth = "LOOP";
                                    break;
                                } else {
                                    VisitedSoberInverted[CurrentL][CurrentC] += CurrentDirection;
                                }
                            } else {
                                if (VisitedDrunkInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                    DirectionToPhoneBooth = "LOOP";
                                    break;
                                } else {
                                    VisitedDrunkInverted[CurrentL][CurrentC] += CurrentDirection;
                                }
                            }
                        }
                        ResetDirection = true;
                        CurrentL = ProcessingCurrentL;
                        CurrentC = ProcessingCurrentC;
                        Map[CurrentL][CurrentC] = ' ';
                        // Reset all the states back to their initial values that Bender can be in.  The map has changed due to Bender destroying obstacles
                        for (int i = 0; i < MaxL; i++)
                            for (int j = 0; j < MaxC; j++) {
                                VisitedSoberNonInverted[i][j] = "";
                                VisitedDrunkNonInverted[i][j] = "";
                                VisitedSoberInverted[i][j] = "";
                                VisitedDrunkInverted[i][j] = "";
                            }
                    }
                    break;
                case 'B':
                    DirectionToPhoneBooth += (DirectionToPhoneBooth != "") ? "\n" + PrintDirection(CurrentDirection) : PrintDirection(CurrentDirection);
                    if (!IsInverted) {
                        if (!BreakerMode) {
                            if (VisitedSoberNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    } else {
                        if (!BreakerMode) {
                            if (VisitedSoberInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    }
                    BreakerMode = !BreakerMode;
                    ResetDirection = true;
                    CurrentL = ProcessingCurrentL;
                    CurrentC = ProcessingCurrentC;
                    break;
                case 'I':
                    DirectionToPhoneBooth += (DirectionToPhoneBooth != "") ? "\n" + PrintDirection(CurrentDirection) : PrintDirection(CurrentDirection);
                    if (!IsInverted) {
                        if (!BreakerMode) {
                            if (VisitedSoberNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    } else {
                        if (!BreakerMode) {
                            if (VisitedSoberInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    }
                    IsInverted = !IsInverted;
                    ResetDirection = true;
                    CurrentL = ProcessingCurrentL;
                    CurrentC = ProcessingCurrentC;
                    break;
                case 'T':
                    DirectionToPhoneBooth += (DirectionToPhoneBooth != "") ? "\n" + PrintDirection(CurrentDirection) : PrintDirection(CurrentDirection);
                    if (!IsInverted) {
                        if (!BreakerMode) {
                            if (VisitedSoberNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkNonInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkNonInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    } else {
                        if (!BreakerMode) {
                            if (VisitedSoberInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedSoberInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        } else {
                            if (VisitedDrunkInverted[CurrentL][CurrentC].indexOf(CurrentDirection) > -1) {
                                DirectionToPhoneBooth = "LOOP";
                                break;
                            } else {
                                VisitedDrunkInverted[CurrentL][CurrentC] += CurrentDirection;
                            }
                        }
                    }
                    if ((Teleport1L == ProcessingCurrentL) && (Teleport1C == ProcessingCurrentC)) {
                        CurrentL = Teleport2L;
                        CurrentC = Teleport2C;
                    } else {
                        CurrentL = Teleport1L;
                        CurrentC = Teleport1C;
                    }
                    break;
            }

            // The code has encountered a looping situation.  Exit the loop prematurely.
            if (DirectionToPhoneBooth == "LOOP")
                break;
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        // Print out the results
        System.out.println(DirectionToPhoneBooth);
    }

    private static char ChangeDirection(char currentDirection, Boolean isInverted) {
        char NewDirection = '\0';

        if (!isInverted) {
            switch (currentDirection) {
                case 'N':
                    NewDirection = 'W';
                    break;
                case 'S':
                    NewDirection = 'E';
                    break;
                case 'E':
                    NewDirection = 'N';
                    break;
            }
        } else {
            switch (currentDirection) {
                case 'N':
                    NewDirection = 'E';
                    break;
                case 'W':
                    NewDirection = 'N';
                    break;
                case 'E':
                    NewDirection = 'S';
                    break;
            }
        }

        return NewDirection;
    }

    private static String PrintDirection(char currentDirection) {
        String PrintDirection = "";

        switch (currentDirection) {
            case 'N':
                PrintDirection = "NORTH";
                break;
            case 'S':
                PrintDirection = "SOUTH";
                break;
            case 'W':
                PrintDirection = "WEST";
                break;
            case 'E':
                PrintDirection = "EAST";
                break;
        }

        return PrintDirection;
    }

    private static char SetCurrentDirection(char currentDirection, Boolean resetDirection, Boolean isInverted) {
        char SetCurrentDirection = '\0';

        if (!isInverted) {
            if (resetDirection && currentDirection != 'S')
                SetCurrentDirection = 'S';
            else
                SetCurrentDirection = ChangeDirection(currentDirection, isInverted);
        } else {
            if (resetDirection && currentDirection != 'W')
                SetCurrentDirection = 'W';
            else
                SetCurrentDirection = ChangeDirection(currentDirection, isInverted);
        }

        return SetCurrentDirection;
    }
}