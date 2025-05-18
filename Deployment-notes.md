# Kubernetes Deployment Notes

## Deploy Order app
<pre>
rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f orderdb-deployment.yml
Warning: resource persistentvolumes/orderdbpv is missing the kubectl.kubernetes.io/last-applied-configuration annotation which is required by kubectl apply. kubectl apply should only be used on resources created declaratively by either kubectl create --save-config or kubectl apply. The missing annotation will be patched automatically.
persistentvolume/orderdbpv configured
Warning: resource persistentvolumeclaims/orderdbpv is missing the kubectl.kubernetes.io/last-applied-configuration annotation which is required by kubectl apply. kubectl apply should only be used on resources created declaratively by either kubectl create --save-config or kubectl apply. The missing annotation will be patched automatically.
persistentvolumeclaim/orderdbpv configured
Warning: resource deployments/om-orderdb-depl is missing the kubectl.kubernetes.io/last-applied-configuration annotation which is required by kubectl apply. kubectl apply should only be used on resources created declaratively by either kubectl create --save-config or kubectl apply. The missing annotation will be patched automatically.
deployment.apps/om-orderdb-depl configured

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl get deployment
NAME              READY   UP-TO-DATE   AVAILABLE   AGE
om-orderdb-depl   0/1     1            0           2m37s

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl get deployment,svc
NAME                              READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/om-orderdb-depl   0/1     1            0           2m49s

NAME                 TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
service/kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   2d19h

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl orderdb-service.yml
error: unknown command "orderdb-service.yml" for "kubectl"

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl create -f orderdb-service.yml
service/om-orderdb created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl create -f order-deployment.yml
deployment.apps/om-order-depl created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl create -f order-service.yml
service/om-order created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl get deployment,svc
NAME                              READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/om-order-depl     0/1     1            0           2m10s
deployment.apps/om-orderdb-depl   0/1     1            0           9m1s

NAME                 TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/kubernetes   ClusterIP   10.96.0.1        <none>        443/TCP          2d19h
service/om-order     NodePort    10.96.23.60      <none>        8080:32080/TCP   107s
service/om-orderdb   ClusterIP   10.101.190.110   <none>        3306/TCP         4m39s

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
</pre>

## Deploy Product app
<pre>
rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f productdb-deployment.yml
persistentvolume/productdbpv created
persistentvolumeclaim/productdbpv created
error: error parsing productdb-deployment.yml: error converting YAML to JSON: yaml: line 57: found unexpected end of stream

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f productdb-deployment.yml
persistentvolume/productdbpv unchanged
persistentvolumeclaim/productdbpv unchanged
deployment.apps/om-productdb-depl created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f productdb-deployment.yml
persistentvolume/productdbpv unchanged
persistentvolumeclaim/productdbpv unchanged
deployment.apps/om-productdb-depl configured

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f productdb-service.yml
service/om-productdb created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f product-deployment.yml
deployment.apps/om-product-depl created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f product-service.yml
The Service "om-product" is invalid: spec.ports[0].nodePort: Invalid value: 32080: provided port is already allocated

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f order-deployment.yml
Warning: resource deployments/om-order-depl is missing the kubectl.kubernetes.io/last-applied-configuration annotation which is required by kubectl apply. kubectl apply should only be used on resources created declaratively by either kubectl create --save-config or kubectl apply. The missing annotation will be patched automatically.
deployment.apps/om-order-depl configured

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f product-service.yml
The Service "om-product" is invalid: spec.ports[0].nodePort: Invalid value: 32080: provided port is already allocated

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl delete -f order-service.yml
service "om-order" deleted

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f order-service.yml
service/om-order created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl apply -f product-service.yml
service/om-product created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl get deployment,svc
NAME                                READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/om-order-depl       0/1     1            0           121m
deployment.apps/om-orderdb-depl     0/1     1            0           128m
deployment.apps/om-product-depl     0/1     1            0           5m51s
deployment.apps/om-productdb-depl   0/1     1            0           7m55s

NAME                   TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/kubernetes     ClusterIP   10.96.0.1        <none>        443/TCP          2d21h
service/om-order       NodePort    10.101.85.68     <none>        8081:32081/TCP   107s
service/om-orderdb     ClusterIP   10.101.190.110   <none>        3306/TCP         123m
service/om-product     NodePort    10.102.214.64    <none>        8080:32080/TCP   100s
service/om-productdb   ClusterIP   10.108.251.206   <none>        27017/TCP        6m3s

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443
Error from server (NotFound): namespaces "kubernetes-dashboard" not found

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
</pre>

## viewing Kubernetes Dashboard
### Install helm
Helm | Installing Helm
https://helm.sh/docs/intro/install/

- Select the port (OS and helm version) for your platform at:
Releases · helm/helm
https://github.com/helm/helm/releases

### Install Kubernetes Dashboard
Deploy and Access the Kubernetes Dashboard | Kubernetes
https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

- Install K8s Dboard:
<pre>
# Add kubernetes-dashboard repository
helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
# Deploy a Helm Release named "kubernetes-dashboard" using the kubernetes-dashboard chart
helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard
</pre>

- Create service account and generate auth token for the same:
<pre>rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl create serviceaccount lokal
serviceaccount/lokal created

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
$ kubectl create token lokal
eyJhbGciOiJSUzI1NiIsImtpZCI6Ik9kWURnX0JzQ1AtZC16VGhHMGYyRHJuQzFGY2doWkI2M2RJSFcyYTlURjgifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiXSwiZXhwIjoxNzQ3NTczODA5LCJpYXQiOjE3NDc1NzAyMDksImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwianRpIjoiMmYwNDlhMjctOTkyOC00NjI4LTg0NjEtNGU4NzEzMGFhNjBlIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJkZWZhdWx0Iiwic2VydmljZWFjY291bnQiOnsibmFtZSI6Imxva2FsIiwidWlkIjoiNjQ0Mzc5ZjUtOTk3NS00YmZhLWIxN2EtOGQ0Y2VmODA2MjQyIn19LCJuYmYiOjE3NDc1NzAyMDksInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0Omxva2FsIn0.f1dis1jemjOTIPUpxZ4Z-w6EMxAIaOGSgrjPmI6NtyoQapRStize6A2nazYcwekBl_2eJfaRskrvkYyn57PVfnvNzXQrNFw5AgknbyaG-7JCpcQCrp9cc43fNC7_ga5MSO8iCgngwsRYoeKf2HpTNjjzveNBWAnGG-8WyborJWyL1uYMXoDw8Odw7KL6tnDi7F4xGE4MBuoBt1KvQRhdkCiB1QpUFNEPQZmO6dBt4PAUcY17lWNYl0pV2Vi7xyYPfS2S5r2Ea7mrqEHZ-l25TIV_ZTbP_U85hrk2eQJuPW5SsEYI4tWVaU2GvoZ5J46K6C79QEehRRwNSsRHeJxFCw

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager/k8sclusteryml/V2kotlin (develop)
</pre>

- Forward the port 8443:
<pre>
kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443
</pre>

- Enjoy the view of Kubernetes Dashboard at `https://localhost:8443`.

## Open issues
### Pods are not ready 
<pre>
rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager (develop)
$ kubectl get pods,deployments,svc -n=default
NAME                                     READY   STATUS             RESTARTS         AGE
pod/om-order-depl-99f49477b-45g7n        0/1     CrashLoopBackOff   45 (4m47s ago)   3h30m
pod/om-orderdb-depl-b65df76c-87k6d       0/1     Pending            0                3h37m
pod/om-product-depl-5d6b878857-5n4rr     0/1     ImagePullBackOff   0                95m
pod/om-productdb-depl-58b47f5646-7mzfj   0/1     Pending            0                97m
pod/om-productdb-depl-69f57c694d-f2pq5   0/1     Pending            0                96m

NAME                                READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/om-order-depl       0/1     1            0           3h30m
deployment.apps/om-orderdb-depl     0/1     1            0           3h37m
deployment.apps/om-product-depl     0/1     1            0           95m
deployment.apps/om-productdb-depl   0/1     1            0           97m

NAME                   TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/kubernetes     ClusterIP   10.96.0.1        <none>        443/TCP          2d23h
service/om-order       NodePort    10.101.85.68     <none>        8081:32081/TCP   91m
service/om-orderdb     ClusterIP   10.101.190.110   <none>        3306/TCP         3h33m
service/om-product     NodePort    10.102.214.64    <none>        8080:32080/TCP   91m
service/om-productdb   ClusterIP   10.108.251.206   <none>        27017/TCP        95m

rishi@ssgamer MINGW64 ~/mygithubprojs/order-manager (develop)
</pre>


## Contact Pointers
- **LinkedIn:** <https://www.linkedin.com/in/rishirajopenminds>
- **X:** <https://twitter.com/RishiRajDevOps>
- **Start Page:** <https://bio.link/rishiraj49de>
- **GitHub:** <https://github.com/rishiraj88>

## Credits and Gratitude
I thank all who have mentored, taught and guided me. Also, I appreciate who have supported my work with pair programming and more.
