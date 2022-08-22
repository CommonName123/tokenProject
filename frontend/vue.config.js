const ForkTsCheckerWebpackPlugin = require('fork-ts-checker-webpack-plugin');

module.exports = {
    outputDir: './dist/',
    publicPath: './',
    pages: {
        app: {
            entry: './src/main/javascript/main.ts',
            template: './src/main/resources/static/index.html',
            filename: 'index.html',
        },
    },
    filenameHashing: false,
    pluginOptions: {
        storybook: {
            allowedPlugins: ['define'],
        },
    },

    configureWebpack: config => {
        // get a reference to the existing ForkTsCheckerWebpackPlugin
        const existingForkTsChecker = config.plugins.filter(
            p => p instanceof ForkTsCheckerWebpackPlugin
        )[0];

        // remove the existing ForkTsCheckerWebpackPlugin
        // so that we can replace it with our modified version
        config.plugins = config.plugins.filter(
            p => !(p instanceof ForkTsCheckerWebpackPlugin)
        );

        // copy the options from the original ForkTsCheckerWebpackPlugin
        // instance and add the memoryLimit property
        if (existingForkTsChecker) {
            const forkTsCheckerOptions = existingForkTsChecker.options;

            // forkTsCheckerOptions.typescript.memoryLimit = 4096;
            config.plugins.push(
                new ForkTsCheckerWebpackPlugin(forkTsCheckerOptions)
            );
        }

        // config.plugins.push(
        //     new CopyWebpackPlugin([
        //         {
        //             from: './src/main/resources/locale',
        //             to: './locale',
        //         },
        //     ])
        // );
    },
};
