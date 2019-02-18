package classes;

public class Banks {
	public static double minAssetLimit;
	public static int numOfBanks;
	public Bank[] bankList;
	int index;

	public Banks(int i, double d) {
		if (i > 0) {
			numOfBanks = i;
			minAssetLimit = d;
			bankList = new Bank[i];
			index = 0;
		} else {
			numOfBanks = -1;
			minAssetLimit = -1;
			index = -1;
		}
	}

	public void addBank(int i, double d) {
		if (index < numOfBanks) {
			bankList[index++] = new Bank(i, d);
		}
	}

	public int[] unsafeList() {
		int tempIndex = 0;
		int[] temp = new int[Banks.numOfBanks];
		for (int i = 0; i < Banks.numOfBanks; i++) {
			temp[i] = -1;
			if (bankList[i].unsafe) {
				temp[tempIndex++] = bankList[i].id;
			}
		}

		for (int i = 0; i < Banks.numOfBanks; i++) {
			if (!bankList[i].unsafe) {

				double sum = bankList[i].balance;
				for (int j = 0; j < bankList[i].banksLoaned; j++) {
					for (int k = 0; k < Banks.numOfBanks; k++) {
						if (bankList[i].loans[j].bankId == bankList[k].id) {
							if (!bankList[k].unsafe) {
								sum += bankList[i].loans[j].amt;
							}
						}
					}
				}

				if (sum < Banks.minAssetLimit) {
					temp[tempIndex++] = bankList[i].id;
				}
			}
		}

		return temp;
	}

}