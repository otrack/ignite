import sys
import os

YCSB_file_name = sys.argv[1]
flag_avg = sys.argv[2]
load_run_flag = sys.argv[3]
nb_client = sys.argv[4]
list_args = sys.argv[5:len(sys.argv)]



if flag_avg == "True":

    list_nb_client = nb_client.split(" ")

    for nb in list_nb_client:

        for arg in list_args:
            arg0 = arg.split("-")[0]
            arg1 = arg.split("-")[1]
            file_name = "YCSB_"+load_run_flag+"_"+arg0+"_"+arg1+"_"+nb+"_clients.txt"
            file_name_avg = "YCSB_"+load_run_flag+"_"+arg0+"_"+arg1+".txt"

            file = open(file_name, "r")

            if nb == "1":
                file_avg = open(file_name_avg, "w")
            else:
                file_avg = open(file_name_avg, "a")

            sum = 0
            nb_test = 0

            for line in file.readlines():
                val = float(line.split(" ")[2])
                sum += val
                nb_test += 1

            file_avg.write(nb + " " + str(sum/nb_test) + "\n")

            file.close()
            file_avg.close()

            os.remove(file_name)
else:

    file_read = open(YCSB_file_name, "r")

    for line in file_read.readlines():
        
        for arg in list_args:

            arg0 = arg.split("-")[0]
            arg1 = arg.split("-")[1]
            file_name = "YCSB_"+load_run_flag+"_"+arg0+"_"+arg1+"_"+nb_client+"_clients.txt"

            if arg0 in line and arg1 in line:

                split_line = line.split(",")
                
                file_write = open(file_name,"a")
                
                # print(nb_client+" "+split_line[len(split_line) - 1])
                file_write.write(nb_client+" "+split_line[len(split_line) - 1])

                file_write.close()
                
    file_read.close()

