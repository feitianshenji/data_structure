/**
 * p16 12个球，11个相同，1个是坏的，找出来。
 * @author feitianshenji
 *
 */

public class chapfindlight {
    public static void main(String[] args) {

        findlight FL = new findlight();
        int a[] = {0,5,5,5,5,5,5,5,4,5,5,5,5};
        System.out.print(FL.find(a));
    }
}

class findlight {

    int find(int a[]) {

        if (a[1]+a[2]+a[3]+a[4] == a[5]+a[6]+a[7]+a[8]) {
            if (a[1]+a[9] == a[10]+a[11]) {
                if (a[1] > a[12]) {
                    return (-12);
                } else {
                    return (12);
                }
            } else if (a[1]+a[9] > a[10]+a[11]) {
                if (a[10] == a[11]) {
                    return (9);
                } else if (a[10] > a[11]) {
                    return (-11);
                } else {
                    return (-10);
                }
            } else {
                if (a[10] == a[11]) {
                    return (-9);
                } else if (a[10] > a[11]) {
                    return (10);
                } else {
                    return (11);
                }
            }
        } else if (a[1]+a[2]+a[3]+a[4] > a[5]+a[6]+a[7]+a[8]) {
            if (a[1]+a[2]+a[6] == a[3]+a[4]+a[5]) {
                if (a[1] == a[7]) {
                    return (-8);
                } else {
                    return (-7);
                }
            } else if (a[1]+a[2]+a[6] > a[3]+a[4]+a[5]) {
                if (a[1] == a[2]) {
                    return (-5);
                } else if (a[1] > a[2]) {
                    return (1);
                } else {
                    return (2);
                }
            } else {
                if (a[3] == a[4]) {
                    return (-6);
                } else if (a[3] > a[4]) {
                    return (3);
                } else {
                    return (4);
                }
            }
        } else {
            if (a[1]+a[2]+a[6] == a[3]+a[4]+a[5]) {
                if (a[1] == a[7]) {
                    return (8);
                } else {
                    return (7);
                }
            } else if (a[1]+a[2]+a[6] > a[3]+a[4]+a[5]) {
                if (a[3] == a[4]) {
                    return (6);
                } else if (a[3] > a[4]) {
                    return (-4);
                } else {
                    return (-3);
                }
            } else {
                if (a[1] == a[2]) {
                    return (5);
                } else if (a[1] > a[2]) {
                    return (-2);
                } else return(-1);
            }
        }
    }
}
