public class Problem3 {
    private static final int MIN_NUMBER_PLACE = 1;
    private static final int MAX_NUMBER_PLACE = 4;

    public static int solution(int number) {
        int answer = 0;

        //number 의 자리수 대로 확인
        // 최대 number 가 10000 (9999) 이므로, 0 ~ 3 번 자리 (천의 자리 ~ 일의 자리) 까지 확인(ex. number 가 6245 라면, 0번 자리: 6, 1번 자리: 2, 2번 자리: 4, 3번 자리: 5)
        for (int i = MIN_NUMBER_PLACE - 1; i <= MAX_NUMBER_PLACE - 1; i++) {
            answer += countClapOnPlace(number , i);
        }
        return answer;
    }

    //각 자리수 (천의 자리, 백의 자리, ... ) 에 대해 누적 박수 합을 계산
    private static int countClapOnPlace(int number, int place) {
        int count = 0; 
        int checkNum; //3, 6, 9 중 이번에 확인하고자 하는 숫자
        int maxFrontPlace = calculateMaxFrontPlace(place); //지금 구하려는 자리수의 앞에 올 수 있는 최대 수를 구한다 (ex. 십의 자리를 구하려고 하면, 십의 자리의 앞에는 0 ~ 99 까지가 올 수 있다) 
        for (checkNum = 3; checkNum <= 9; checkNum += 3) { //이번 자리에 3, 6, 9 가 올 때의 모든 박수 수 계산
            count += countNowCheckNum(checkNum, place, number, maxFrontPlace);
        }
        return count;
    }
    
    /**
     * 3, 6, 9 중 이번 숫자 (nowCheckNum) 가 이번 자릿수 (place) 에 올 수 있는 모든 경우의 수를 연산한다.
     * @param checkNum 3, 6, 9 중 이번에 체크할 수
     * @param place 자릿수 (0: 천의 자리, 1: 백의 자리, 2: 십의 자리, 3: 일의 자리) 
     * @param number 타겟 숫자 (최대 숫자) 
     * @param maxFrontPlace 현재 확인하고자 하는 자릿수의 앞에 올 수 있는 최대 숫자 (ex. 십의 자리를 확인하려하면 ~ 99__ 이므로 maxFrontPlace = 99) 
     * @return 이번 숫자의 박수 수
     */
    private static int countNowCheckNum(int checkNum, int place, int number, int maxFrontPlace) {
        int frontPlace;
        int thisNumber;
        int count = 0;

        for (frontPlace = 0; frontPlace <= maxFrontPlace; frontPlace++) { // 십의 자리라면: 0 ~ 99까지 앞 자리로 올 수 있음
            //현재 십의 자리 수가 3이라면, 003_ ~ 993_ 까지 올 수 있다
            //천의 자리라면, maxFrontPlace == 0이므로, 3___ ~ 9___ 까지 한 번씩 만 연산하게 된다.
            
            thisNumber = calculateThisNumber(place, frontPlace, checkNum); //이번 숫자를 연산
           int thisNumberClapCount = checkThisNumberCount(thisNumber, number, place);
            if (thisNumberClapCount == -1) {
                break;
            }
            count += thisNumberClapCount;
        }
        return count;
    }

    //각 자리수 별로 앞 자리에 최대로 올 수 있는 숫자를 구한다
    //ex. 십의 자리 수에 올 수 있는 누적 3, 6, 9 개수의 합을 구한다면
    //0 ~ 99 + 십의 자리 + 1의 자리 => maxFrontPlace == 99
    //십의 자리 수의 place == 2 (0: 천의 자리, 1 : 백의 자리, 2: 십의 자리, 3: 일의 자리) -> maxFrontNum == 9 * 11이 된다
    private static int calculateMaxFrontPlace(int place) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = 1; i <= place; i++) {
            sb.append("1");
        }
        int maxNumber = Integer.parseInt(sb.toString());
        maxNumber *= 9;
        return maxNumber;
    }

    /**
     * 이번 숫자를 계산한다 
     * @param place 자리 위치 (0, 1, 2, 3) 중 
     * @param frontPlace 이번 자리의 앞에 오는 숫자
     * @param nowCheckNum 3, 6, 9 중 이번에 확인하고자 하는 숫자
     * @return
     */
    private static int calculateThisNumber(int place, int frontPlace, int nowCheckNum) {
        int frontNumber = calculateFrontNumber(place, frontPlace); //십의 자리의 3, 6, 9 개수를 구하고 있으며, 앞 자리에 13 -> 즉 133_를 계산하는 경우라면
        //frontNumber == 1300
        int midNumber = nowCheckNum * calculateDigit(place);
        //3, 6, 9 * (1, 10, 100, 1000 중 이번 place 에 해당하는 수)  => 30 or 60 or 90 (십의 자리인 경우)
        int lastNumber = calculateMaxFrontPlace(3 - place); //십의 자리인 경우: 뒤에 9 만 붙는다 -> 1339 가 앞 자리가 13, 십의 자리가 3 인 최대수

        int thisNumber = frontNumber + midNumber + lastNumber; //앞, 중간, 뒤  숫자의 합으로 이번 숫자를 연산
        return thisNumber;
    }

    //현재 자리 번호 (0,1,2,3) 에 따라서 몇 자리 인지 계산
    //십의 자리라면: place = 2, digit = 10 (10^1)
    //백의 자리라면: place = 1, digit = 100 (10^2)
    private static int calculateDigit(int place) {
        int digit = (int)Math.pow(10, 3 - place);
        return digit;
    }

    /**
     * 이번 숫자의 박수 수를 연산한다
     * @param thisNumber 이번 숫자 (ex. 1999, 2999) 
     * @param number 최대 숫자 (타겟 숫자)
     * @param place 자릿수
     * @return 박수 수
     */
    private static int checkThisNumberCount(int thisNumber, int number, int place) {
        int digit = calculateDigit(place); // 이번 숫자가 십의 자리 수를 구하는 거라면?  + 10
        
        int count = -1;
        if (thisNumber <= number) { //백의 자리 수가 3, 6, 9 일 때를 구하는거라면? 1399 < 1509 일 때, 1300 ~ 1399 까지가 모두 포함되므로 백의 자리에 3이 오는 경우의 수는 100 (=digit)
            count = digit;
            return count;
        }
        
        //앞 자리가 같은 경우 (ex. 1699, 1609 -> 15 == 15) 1600 ~ 1609 까지가 백의 자리에 6이 올 수 있는 모든 경우의 수 => 9 + 1 만큼 더해진다
        if (isFrontNumberSame(thisNumber, number, digit)) { //앞 숫자가 같다면, ex) thisNumber = 1339, Number = 1336 -> 0 ~ 6 즉 7개만 카운트
            int possibleCount = number % digit;
            count = possibleCount + 1;
            return count;
        }
        return count;
    }

    /**
     * 맨 앞 자리수가 같은지 계산한다. ex) 십의 자리를 구하는 경우, thisNumber = 1399, number = 1356 -> 13 == 13 이므로 true
     * @param thisNumber 체크하고자 하는 이번 숫자
     * @param number 최대 숫자 (타겟 숫자)
     * @param digit 이번에 확인하는 자릿수
     * @return
     */
    private static Boolean isFrontNumberSame(int thisNumber, int number, int digit) {
        int thisFrontNumber = thisNumber / digit;
        int frontNumber = number / digit;
        if (thisFrontNumber == frontNumber) {
            return true;
        }
        return false;
    }

    //십의 자리의 3, 6, 9 개수를 구하고 있으며, 앞 자리에 13 -> 즉 13__를 계산하는 경우라면
    //place = 2, frontPlace = 13 -> frontNumber = 1300
    /**
     * 이번 자리의 앞에 오는 숫자를 연산한다 ex) 십의 자리 (place = 2) 에 대해 연산, frontPlace = 13 이라면 13 * 10^2 = 1300 즉 13__ 가 된다. 
     * @param place 이번 자리의 위치
     * @param frontPlace 이번 자리의 앞에 오는 숫자 
     * @return 연산 결과
     */
    private static int calculateFrontNumber(int place, int frontPlace) {
        int leadingDigit  = (int)Math.pow(10, (4 - place));
        int frontNumber = frontPlace * leadingDigit;
        return frontNumber;
    }

}