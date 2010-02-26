killall sipp
export TEST_IP=127.0.0.1

export MSS_IP=$TEST_IP
export LB_IP=$TEST_IP
export SIPP_IP=$TEST_IP

echo Using $MSS_IP as the MSS test IP
echo Using $LB_IP as the Load Balancer IP
echo Using $SIPP_IP as the SIPp IP

if [ $# -ne 0 ]; then
	case $1 in	
	    proxy)
	    		rm ./proxy/*.log
	    		echo "Distributed example used is proxy";
	    		./sipp $MSS_IP:5080 -sf proxy/location-service-receiver.xml -i $SIPP_IP -p 5090 -bg -trace_msg -timeout 30  
	    		./sipp $MSS_IP:5080 -s receiver-failover -sf proxy/location-service-sender.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg
	            ;;
	    proxy-remote-send-bye)
	    		rm ./proxy/*.log
	    		echo "Distributed example used is proxy";
	    		./sipp $MSS_IP:5080 -sf proxy/location-service-receiver-sends-bye.xml -i $SIPP_IP -p 5090 -bg -trace_msg -timeout 30 -nd
	    		./sipp $MSS_IP:5080 -s receiver-failover -sf proxy/location-service-sender-receives-bye.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd
	            ;;
	    b2bua)
	    		rm ./b2bua/*.log
	            echo "Distributed example used is b2bua";
	            ./sipp $MSS_IP:5080 -sf b2bua/call-forwarding-receiver.xml -i $SIPP_IP -p 5090 -bg -trace_msg -timeout 20  
	    		./sipp $MSS_IP:5080 -s receiver -sf b2bua/call-forwarding-sender.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg
	            ;;
	    b2bua-remote-send-bye)
	    		rm ./b2bua/*.log
	            echo "Distributed example used is b2bua-remote-send-bye";
	            ./sipp $MSS_IP:5080 -sf b2bua/call-forwarding-receiver-sends-bye.xml -i $SIPP_IP -p 5090 -bg -trace_msg -timeout 30 
	    		./sipp $MSS_IP:5080 -s receiver -sf b2bua/call-forwarding-sender-receives-bye.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd
	            ;;
		uac)
				rm ./uac/*.log
	            echo "Distributed example used is uac";
	            ./sipp $MSS_IP:5080 -sf uac/receiver.xml -i $SIPP_IP -p 5090 -trace_msg -timeout 30
	            ;;
		c2c)
				rm ./converged-click2call/*.log
	            echo "Distributed example used is 3pcc";
	            echo "Now, quickly navigate a browser to this URL: http://"$MSS_IP"/click2call-distributable/call?to=sip:to@"$SIPP_IP":5090&from=sip:from@"$SIPP_IP":5091";
	            echo "Then, wait a few secs for the dialog to be established and kill the primary server. The secondary server will take over.";
	            echo "40 secs after you have loaded the URL, one of the SIPP clients will send a BYE to close the dialog.";
	            echo "If nothing happens, check if your apache server with mod_jk installed is running and configured properly.";
	            echo "There are sample config files to run apache and mod_jkon localhost in the converged-click2call directory.";
	            echo "If you need more info about how to configure mod_jk see http://www.jboss.org/community/docs/DOC-12525";
                killall sipp
	            ./sipp $MSS_IP:5080 -sf converged-click2call/receiver.xml -i $SIPP_IP -p 5090 -trace_msg -timeout 100 -bg
                ./sipp $MSS_IP:5080 -sf converged-click2call/receiver-sendbye.xml -i $SIPP_IP -p 5091 -trace_msg -timeout 100 -bg
                sleep 1
                #wget "http://$MSS_IP/click2call-distributable/call?to=sip:to@$SIPP_IP:5090&from=sip:from@$SIPP_IP:5091"
	            ;;
	    uas-reinvite)
	    		rm ./uas/*.log
	            echo "Distributed example used is uas";
	    		./sipp $MSS_IP:5080 -s reinvite -sf uas/clustering-reinvite-uac.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd
	    		;;
	    uas-no-attributes)
	    		rm ./uas/*.log
	            echo "Distributed example used is uas";
	    		./sipp $MSS_IP:5080 -s NoAttributes -sf uas/clustering-uac-no-attrs.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd
	    		;;
	    uas-timer)
	    		rm ./uas/*.log
	            echo "Distributed example used is uas";
	    		./sipp $MSS_IP:5080 -s yousendbye -sf uas/clustering-uac-timer.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd
	            ;;
	    uas-sas-timer)
	    		# kill first node after the ACK
	    		rm ./uas/*.log
	            echo "Distributed example used is uas";
	    		./sipp $MSS_IP:5080 -s sastimersendbye -sf uas/clustering-uac-timer.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd	    		
	            ;;
		uas-reinvite-sas-timer)
	    		# kill first node after the first ACK
	    		rm ./uas/*.log
	            echo "Distributed example used is uas";
	    		./sipp $MSS_IP:5080 -s sastimersendbye -sf uas/clustering-reinvite-sas-timer.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd	    		
	            ;;
	    *)
	    		rm ./uas/*.log
	            echo "Distributed example used is uas";
	    		./sipp $MSS_IP:5080 -s isendbye -sf uas/clustering-uac.xml -trace_err -i $SIPP_IP -p 5050 -r 1 -m 1 -rsa $LB_IP:5060 -trace_msg -nd
	            ;;
    esac
fi