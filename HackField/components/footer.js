import Link from "next/link";
import Image from "next/image";
import React from "react";
import Container from "./container";
import SocialMedia from "./socialMedia";

export default function Footer() {
  const navigation = [
    "Practice",
    "Compete",
    "Team",
    "Learn",
  ];
  const legal = [];
  return (
    <div className="relative">
      <Container>
        <div className="grid max-w-screen-xl grid-cols-1 gap-10 pt-10 mx-auto mt-5 border-t border-gray-100 dark:border-trueGray-700 lg:grid-cols-5">
          <div className="lg:col-span-2">
            <div>
              {" "}
              <Link href="/" className="flex items-center space-x-2 text-2xl font-medium text-white dark:text-gray-100">
                    <Image
                      src="/img/logo.svg"
                      alt="N"
                      width="32"
                      height="32"
                      className="w-8"
                    />
                  <span>Hack<span className="text-teal-200 dark:text-teal-400 ">Field</span></span>
              </Link>
            </div>

            <div className="max-w-md mt-4 text-gray-500 dark:text-gray-400">
              HackField : CTF Plateforme created by Ensa students, and destined to Ensa Student
            </div>

            
          </div>

          <div>
            <div className="flex flex-wrap w-full -mt-2 -ml-3 lg:ml-0">
              {navigation.map((item, index) => (
                <Link key={index} href="/" className="w-full px-4 py-2 text-gray-500 rounded-md dark:text-gray-300 hover:text-indigo-500 focus:text-indigo-500 focus:bg-indigo-100 focus:outline-none dark:focus:bg-trueGray-700">                 
                    {item}
                </Link>
              ))}
            </div>
          </div>
          <div>
            <div className="flex flex-wrap w-full -mt-2 -ml-3 lg:ml-0">
              {legal.map((item, index) => (
                <Link key={index} href="/" className="w-full px-4 py-2 text-gray-500 rounded-md dark:text-gray-300 hover:text-indigo-500 focus:text-indigo-500 focus:bg-indigo-100 focus:outline-none dark:focus:bg-trueGray-700">      
                    {item}
                </Link>
              ))}
            </div>
          </div>
          <div className="">
            <div>Follow us</div>
            <div className="flex mt-5 space-x-5 text-teal-400 dark:text-white">

              <SocialMedia size="24" />
              
            </div>
          </div>
        </div>

      </Container>
      
    </div>
  );
}


